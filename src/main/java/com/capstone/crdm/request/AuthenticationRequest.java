package com.capstone.crdm.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private String username;

    private String password;

    private String refreshToken;

    private String grantType = "password";

}
