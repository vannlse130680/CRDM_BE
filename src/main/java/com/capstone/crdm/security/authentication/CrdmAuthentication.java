package com.capstone.crdm.security.authentication;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class CrdmAuthentication implements Authentication {

    private CrdmUserDetails details;

    private Collection<? extends GrantedAuthority> authorities;

    private String principal;

    private String credentials;

    private boolean authenticated;

    public CrdmAuthentication(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

    public CrdmAuthentication(String principal, CrdmUserDetails details, boolean authenticated) {
        this.principal = principal;
        this.details = details;
        this.authorities = details.getAuthorities();
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return this.getCredentials();
    }

    @Override
    public Object getDetails() {
        return this.details;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.details.getUsername();
    }


}
