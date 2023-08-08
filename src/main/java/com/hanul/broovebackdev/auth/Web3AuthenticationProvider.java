package com.hanul.broovebackdev.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;

@Component
public class Web3AuthenticationProvider implements AuthenticationProvider {

    private final UserService users;

    public Web3AuthenticationProvider(UserService users) {
        this.users = users;
    }

    private Boolean valid(String signature, String address, String nonce) {
        String r = signature.substring(0, 66);
        String s = String.format("0x%s", signature.substring(66, 130));
        String v = String.format("0x%s", signature.substring(130, 132));

        var data = new Sign.SignatureData(
                Numeric.hexStringToByteArray(v),
                Numeric.hexStringToByteArray(r),
                Numeric.hexStringToByteArray(s)
        );

        BigInteger key = null;
        try {
            key = Sign.signedPrefixedMessageToKey(nonce.getBytes(), data);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }

        return matches(key, address);
    }

    private Boolean matches(BigInteger key, String address) {
        return String.format("0x%s", Keys.getAddress(key).toLowerCase()).equals(address.toLowerCase());
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = users.findByAddress(authentication.getName());
        if(user == null)
            throw new NullPointerException();
        String signature = authentication.getCredentials().toString();
        if (valid(signature, user.getAddress(), user.getNonce())) {
            return new Web3Authentication(user.getAddress(), signature);
        }
        throw new BadCredentialsException(String.format("%s is not allowed to log in.", authentication.getName()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Web3Authentication.class.equals(authentication);
    }
}
