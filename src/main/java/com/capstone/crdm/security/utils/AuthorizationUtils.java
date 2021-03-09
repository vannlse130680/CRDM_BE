package com.capstone.crdm.security.utils;

import com.capstone.crdm.security.authentication.CrdmAuthentication;
import com.capstone.crdm.security.authentication.CrdmRole;
import com.capstone.crdm.security.authentication.CrdmUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

public class AuthorizationUtils {

    public static Integer getCurrentUserId() {
        return ((CrdmUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getId();
    }

    public static String getCurrentUserRole() {
        return ((CrdmUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRole();
    }

    /**
     * Set security context to "SYSTEM"
     */
    @SuppressWarnings("unchecked")
    public static void toSystemAuthentication() {
        // grant authorities and update security context
        CrdmUserDetails userDetails = new CrdmUserDetails();

        userDetails.setId(0);
        userDetails.setGoogleId(null);
        userDetails.setRole("system");

        var finalUserAuthorities = ((ArrayList<SimpleGrantedAuthority>) userDetails.getAuthorities());
        finalUserAuthorities.add(new SimpleGrantedAuthority("ROLE_system"));
        finalUserAuthorities.add(new SimpleGrantedAuthority("ROLE_" + CrdmRole.STAFF.name()));

        SecurityContextHolder.getContext().setAuthentication(new CrdmAuthentication("system", userDetails, true));
    }

    public static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
