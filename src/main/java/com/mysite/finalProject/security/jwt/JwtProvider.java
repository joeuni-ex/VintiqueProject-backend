package com.mysite.finalProject.security.jwt;

import com.mysite.finalProject.security.UserPrinciple;

public interface JwtProvider {
    String generateToken(UserPrinciple auth);
}
