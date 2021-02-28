package com.capstone.crdm.security.authenticator;

import com.capstone.crdm.exception.CrdmIllegalArgumentException;
import com.capstone.crdm.security.authentication.CrdmAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CrdmAuthenticator implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            throw new CrdmIllegalArgumentException("Authentication must not be null");
        }

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CrdmAuthentication.class.equals(authentication);
    }

}
