package com.capstone.crdm.security.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class CrdmUserDetails implements UserDetails {

    private String username;

    private Integer id;

    private String googleId;

    private String role;

    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    private boolean accountNonLocked = false;

    private boolean accountNonExpired = false;

    private boolean credentialsNonExpired = false;

    private boolean enabled = true;

    private String accessToken;

    public CrdmUserDetails() {
        this.authorities.add(new SimpleGrantedAuthority("ROLE_standard_user"));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

}
