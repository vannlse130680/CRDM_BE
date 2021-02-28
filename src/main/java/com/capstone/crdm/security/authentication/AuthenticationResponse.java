package com.capstone.crdm.security.authentication;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class AuthenticationResponse {

    private String accessToken;

    private Instant expiredAt;

    private String refreshToken;

    private Instant refreshTokenExpiredAt;

    private String tokenType;

    private List<String> scope;

}
