package com.interim.service;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton

public class JwtService {
    @Transactional
    public String generateJwt(String role,Long userId) {

        return Jwt.issuer("interim-jwt")
                .subject("interim-cart")
                .groups(role)
                .claim("userId", userId)
                .expiresAt(System.currentTimeMillis() +3600 )
                .sign();

    }
}
