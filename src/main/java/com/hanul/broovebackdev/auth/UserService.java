package com.hanul.broovebackdev.auth;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public void register(User user){
        this.users.put(user.getAddress(), user);
    }

    public User findByAddress(String address){
        return this.users.get(address);
    }
}
