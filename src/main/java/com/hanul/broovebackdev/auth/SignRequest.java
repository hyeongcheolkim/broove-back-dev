package com.hanul.broovebackdev.auth;

import lombok.Data;

@Data
public class SignRequest {
    private String signature;
    private String address;
}

