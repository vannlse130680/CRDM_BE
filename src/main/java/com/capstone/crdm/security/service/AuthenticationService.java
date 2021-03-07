package com.capstone.crdm.security.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.exception.CrdmIllegalArgumentException;
import com.capstone.crdm.exception.CrdmIllegalStateException;
import com.capstone.crdm.exception.CrdmUnauthorizedException;
import com.capstone.crdm.repositories.UserRepository;
import com.capstone.crdm.request.AuthenticationRequest;
import com.capstone.crdm.security.authentication.AuthenticationResponse;
import com.capstone.crdm.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CrdmTokenService tokenService;

    @Value("${crdm.security.encryption.keystore.alias}")
    private String keyAlias;

    @Value("${crdm.security.encryption.keystore.password}")
    private String keyPassword;

    @Value("${crdm.security.encryption.keystore.keystore-name}")
    private String keystoreName;

    private JWTVerifier jwtVerifier;

    public AuthenticationService() {
        // prepare algorithm
    }

    @PostConstruct
    public void initializeVerifier() {
        this.prepareVerifier();
    }

    public AuthenticationResponse getAccessToken(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null) {
            throw new CrdmIllegalArgumentException("Authentication request must not be null");
        }

        var grantType = authenticationRequest.getGrantType();
        if (grantType == null || grantType.isBlank()) {
            throw new CrdmIllegalArgumentException("Grant type must be specified");
        }

        if (!grantType.equals("password")) {
            throw new CrdmUnauthorizedException("Invalid grant type");
        }

        if (authenticationRequest.getUsername() == null) {
            throw new CrdmIllegalArgumentException("Username must not be null");
        }

        if (authenticationRequest.getPassword() == null) {
            throw new CrdmIllegalArgumentException("Password must not be null");
        }

        var user = this.userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new CrdmUnauthorizedException("User does not exist"));
        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            throw new CrdmUnauthorizedException("Invalid password for user " + user.getUsername());
        }

        return this.createAuthenticationResponse(user);
    }

    public AuthenticationResponse refreshToken(AuthenticationRequest authenticationRequest) {
        // verify and extract key
        DecodedJWT jwt;
        var grantType = authenticationRequest.getGrantType();
        if (grantType == null || grantType.isBlank()) {
            throw new CrdmUnauthorizedException("Grant type must be specified");
        }

        if (grantType.equals("refresh_token")) {
            throw new CrdmUnauthorizedException("Invalid grant type");
        }

        var refreshToken = authenticationRequest.getRefreshToken();
        try {
            jwt = this.jwtVerifier.verify(refreshToken);
        } catch (JWTVerificationException ex) {
            throw new CrdmUnauthorizedException("Invalid refresh token. Detailed: " + ex.getLocalizedMessage());
        }

        if (!"Refresh".equals(jwt.getClaim("typ").asString())) {
            throw new CrdmUnauthorizedException("Invalid refresh token. Detailed: This token is not a 'refresh' token");
        }

        UserEntity user = this.userRepository.findById(Integer.parseInt(jwt.getSubject())).orElseThrow(() -> new CrdmIllegalStateException("Requested user does not exist in database anymore."));
        return this.refreshAuthentication(user, refreshToken, jwt.getExpiresAt().toInstant());
    }

    protected AuthenticationResponse createAuthenticationResponse(UserEntity user) {
        if (!user.getStatus().equals("ACTIVE")) {
            throw new CrdmUnauthorizedException("This account has been suspended. Please contact service manager.");
        }

        return this.tokenService.createAuthenticationResponse(user);
    }

    protected AuthenticationResponse refreshAuthentication(UserEntity user, String refreshToken, Instant expiresAt) {
        if (!user.getStatus().equals("ACTIVE")) {
            throw new CrdmUnauthorizedException("This account has been suspended. Please contact service administrator.");
        }

        return this.tokenService.refreshAuthenticationResponse(user, refreshToken, expiresAt);
    }

    private void prepareVerifier() {
        this.jwtVerifier = JwtUtils.initializeVerifier(this.keystoreName, this.keyAlias, this.keyPassword);
    }

}
