package com.hanul.broovebackdev.auth;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Init {

    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    void initMethod(){
        userService.register(new User("0x930313dff04546D782E2223547176351321617cA"));
    }
}
