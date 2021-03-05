package com.capstone.crdm.security.service;

import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.security.authentication.AuthenticationResponse;
import com.capstone.crdm.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

@Service
public class CrdmTokenService {

    private static final String BEARER_TYPE = "bearer";

    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationResponse createAuthenticationResponse(UserEntity userEntity) {
        AuthenticationResponse response = new AuthenticationResponse();

        response.setTokenType(BEARER_TYPE);

        this.jwtUtils.createAccessToken(response, userEntity);
        this.jwtUtils.createRefreshToken(response, userEntity);

        // 1 - MANAGER, 2 - STAFF
        if (userEntity.getRoleId() == 1) {
            response.setScope(Arrays.asList("manage-clients", "manage-formulas", "manage-materials", "manage-projects", "manage-users"));
        } else if (userEntity.getRoleId() == 2) {
            response.setScope(Arrays.asList("manage-clients", "manage-formulas", "manage-materials", "manage-projects"));
        }

        return  response;
    }

    public AuthenticationResponse refreshAuthenticationResponse(UserEntity userEntity, String refreshToken, Instant expiresAt) {
        AuthenticationResponse response = new AuthenticationResponse();

        response.setTokenType(BEARER_TYPE);

        var decodedToken = this.jwtUtils.decode(refreshToken);
        var exp = Instant.ofEpochSecond(decodedToken.getClaim("exp").asLong());

        this.jwtUtils.createAccessToken(response, userEntity);
        response.setRefreshToken(refreshToken);
        response.setRefreshTokenExpiredAt(exp);

        // 1 - MANAGER, 2 - STAFF
        if (userEntity.getRoleId() == 1) {
            response.setScope(Arrays.asList("manage-clients", "manage-formulas", "manage-materials", "manage-projects", "manage-users"));
        } else if (userEntity.getRoleId() == 2) {
            response.setScope(Arrays.asList("manage-clients", "manage-formulas", "manage-materials", "manage-projects"));
        }

        return  response;
    }

}
