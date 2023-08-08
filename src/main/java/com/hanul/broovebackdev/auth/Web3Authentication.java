package com.hanul.broovebackdev.auth;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class Web3Authentication extends AbstractAuthenticationToken {
    private final String address;
    private final String signature;

    public Web3Authentication(String address, String signature) {
        super(null);
        this.address = address;
        this.signature = signature;
    }

    @Override
    public Object getCredentials() {
        return this.signature;
    }

    @Override
    public Object getPrincipal() {
        return this.address;
    }
}
