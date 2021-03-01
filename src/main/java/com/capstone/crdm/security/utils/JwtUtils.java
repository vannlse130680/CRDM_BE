package com.capstone.crdm.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.capstone.crdm.entities.User;
import com.capstone.crdm.exception.CrdmIllegalArgumentException;
import com.capstone.crdm.exception.CrdmIllegalStateException;
import com.capstone.crdm.security.authentication.AuthenticationResponse;
import com.capstone.crdm.security.authentication.CrdmRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@SuppressWarnings("unused")
@Slf4j
@Service
public class JwtUtils {

    @Value("${crdm.security.encryption.keystore.alias}")
    private String keyAlias;

    @Value("${crdm.security.encryption.keystore.password}")
    private String keyPassword;

    @Value("${crdm.security.encryption.keystore.keystore-name}")
    private String keystoreName;

    @Value("${crdm.authentication.access-token.lifetime:2}")
    private Integer accessTokenLifetime;

    @Value("${crdm.authentication.refresh-token.lifetime:720}")
    private Integer refreshTokenLifetime;

    private Algorithm algorithm;

    private JWTVerifier jwtVerifier;

    public void createAccessToken(AuthenticationResponse response, User user) {
        if (response == null) {
            throw new CrdmIllegalArgumentException("Response instance must not be null");
        }

        if (user == null) {
            throw new CrdmIllegalArgumentException("User must not be null");
        }

        if (this.algorithm == null) {
            this.prepareKey();
        }

        var expiresAt = Instant.now().plus(this.accessTokenLifetime, ChronoUnit.HOURS);

        var accessToken = JWT.create()
                .withIssuer("crdm")
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(expiresAt))
                .withAudience("crdm-services")
                .withSubject(String.valueOf(user.getId()))
                .withClaim("typ", "Bearer")
                .withClaim("role", this.prepareRole(user))
                .withArrayClaim("authorities", this.prepareAuthorities(user))
                .sign(this.algorithm);

        response.setAccessToken(accessToken);
        response.setExpiredAt(expiresAt);
    }

    public void createRefreshToken(AuthenticationResponse response, User user) {
        if (response == null) {
            throw new CrdmIllegalArgumentException("Response instance must not be null");
        }

        if (user == null) {
            throw new CrdmIllegalArgumentException("User must not be null");
        }

        if (this.algorithm == null) {
            this.prepareKey();
        }

        var expiresAt = Instant.now().plus(this.refreshTokenLifetime, ChronoUnit.HOURS);

        var refreshToken = JWT.create()
                .withIssuer("crdm")
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(expiresAt))
                .withAudience("crdm-services")
                .withClaim("typ", "Refresh")
                .withSubject(String.valueOf(user.getId()))
                .sign(this.algorithm);

        response.setRefreshToken(refreshToken);
        response.setRefreshTokenExpiredAt(expiresAt);
    }

    private String prepareRole(User user) {
        if (user.getRoleId() == 1) {
            return CrdmRole.MANAGER.name();
        }

        return CrdmRole.STAFF.name();
    }

    private String[] prepareAuthorities(User user) {
        return new String[]{ };
    }

    private void prepareKey() {
        //RSA
        if (this.keyPassword == null || this.keyPassword.isBlank()) {
            throw new CrdmIllegalStateException("Token signing key is not set up.");
        }

        var keyFactory = new KeyStoreKeyFactory(new ClassPathResource(this.keystoreName), this.keyPassword.toCharArray());
        log.info("Initialize key factory completed.");
        var keyPair = keyFactory.getKeyPair(this.keyAlias);

        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.algorithm = Algorithm.RSA256(publicKey, privateKey);
    }



    public DecodedJWT decode(String refreshToken) {
        this.prepareVerifier();
        return this.jwtVerifier.verify(refreshToken);
    }

    private void prepareVerifier() {
        if (this.jwtVerifier != null) {
            return;
        }

        if (keyPassword == null || keyPassword.isBlank()) {
            throw new CrdmIllegalStateException("Token signing key is not set up.");
        }

        var keyFactory = new KeyStoreKeyFactory(new ClassPathResource(keystoreName), keyPassword.toCharArray());
        log.info("Initialized key factory.");
        var keyPair = keyFactory.getKeyPair(keyAlias);
        this.jwtVerifier = JwtUtils.initializeVerifier(keystoreName, keyAlias, keyPassword);
    }

    public static JWTVerifier initializeVerifier(String keystoreName, String keyAlias, String keyPassword) {
        if (keyPassword == null || keyPassword.isBlank()) {
            throw new CrdmIllegalStateException("Token signing key is not set up.");
        }

        var keyFactory = new KeyStoreKeyFactory(new ClassPathResource(keystoreName), keyPassword.toCharArray());
        log.info("Initialized key factory.");
        var keyPair = keyFactory.getKeyPair(keyAlias);
        return JWT
                .require(Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate()))
                .withIssuer("crdm")
                .withAudience("crdm-services")
                .build();
    }

}
