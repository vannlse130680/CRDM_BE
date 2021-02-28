package com.capstone.crdm.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.capstone.crdm.exception.CrdmIllegalStateException;
import com.capstone.crdm.security.authentication.CrdmAuthentication;
import com.capstone.crdm.security.authentication.CrdmRole;
import com.capstone.crdm.security.authentication.CrdmUserDetails;
import com.capstone.crdm.security.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
public class JwtExtracterFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String BEARER = "Bearer ";

    @Value("${crdm.security.encryption.keystore.alias}")
    private String keyAlias;

    @Value("${crdm.security.encryption.keystore.password}")
    private String keyPassword;

    @Value("${crdm.security.encryption.keystore.keystore-name}")
    private String keystoreName;

    private JWTVerifier jwtVerifier;

    @SuppressWarnings("unchecked")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // extract access token from the request header
        var authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        if (authorizationHeader != null && !authorizationHeader.isBlank()) {
            try {
                var accessToken = authorizationHeader.substring(BEARER.length());

                // prepare algorithm
                this.prepareVerifier();

                // verify and extract key
                DecodedJWT jwt;
                try {
                    jwt = this.jwtVerifier.verify(accessToken);
                } catch (JWTVerificationException ex) {
                    log.warn("Stopped access to {} from an unauthorized user. Detailed: {}", request.getRequestURI(), ex.getLocalizedMessage());
                    response.setHeader("WWW-Authenticate", "Full authentication required to access this resource. Detailed: " + ex.getLocalizedMessage());
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Full authentication required to access this resource. Detailed: " + ex.getLocalizedMessage());
                    return;
                }

                // grant authorities and update security context
                var userDetails = new CrdmUserDetails();

                userDetails.setId(Integer.parseInt(jwt.getSubject()));
                userDetails.setRole(jwt.getClaim("role").asString());

                var finalUserAuthorities = ((ArrayList<SimpleGrantedAuthority>) userDetails.getAuthorities());

                var role = jwt.getClaim("role").asString();
                finalUserAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                if (role.equals(CrdmRole.MANAGER.name())) {
                    finalUserAuthorities.add(new SimpleGrantedAuthority("ROLE_" + CrdmRole.MANAGER.name()));
                }

                var authorities = jwt.getClaim("authorities");
                finalUserAuthorities.addAll(authorities.asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(new CrdmAuthentication(null, userDetails, true));
            } catch (Exception ex) {
                log.warn("PARSE AUTHENTICATION INFORMATION ERROR: {}", ex, ex);
                filterChain.doFilter(request, response);
            }

        }

        filterChain.doFilter(request, response);
    }

    private void prepareVerifier() {
        if (this.jwtVerifier != null) {
            return;
        }

        this.jwtVerifier = JwtUtils.initializeVerifier(this.keystoreName, this.keyAlias, this.keyPassword);
    }

}
