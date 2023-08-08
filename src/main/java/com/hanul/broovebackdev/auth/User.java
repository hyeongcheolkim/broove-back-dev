package com.hanul.broovebackdev.auth;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    public User(String address) {
        this.address = address;
    }

    String address;
    String nonce = UUID.randomUUID().toString();
}
