package com.hanul.broovebackdev.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {
    private final UserService users;
    private final AuthenticationManager manager;

    @GetMapping("/challenge/{address}")
    public String challenge(@PathVariable("address") String address) {
        User user = users.findByAddress(address);
        if (user == null)
            throw new NullPointerException();
        return user.getNonce();
    }

    @PostMapping("/auth")
    public Authentication auth(@RequestBody SignRequest request){
        return manager.authenticate(new Web3Authentication(request.getAddress(), request.getSignature()));
    }
}
