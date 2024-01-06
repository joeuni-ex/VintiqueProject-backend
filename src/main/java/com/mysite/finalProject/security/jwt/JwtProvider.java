package com.mysite.finalProject.security.jwt;

import com.mysite.finalProject.security.UserPrinciple;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(UserPrinciple auth);

    Authentication getAuthentication(HttpServletRequest request);

    //토큰의 유효성 체크 (유저 정보와 날짜 체크)
    boolean isTokenValid(HttpServletRequest request);

    //JwtProviderImpl 리퀘스트의 토큰에서 암호풀어 Claims 가져오기
    //토큰에서 유저 정보 CLAIM 부분만 가져옴
    Claims extracClaims(HttpServletRequest request);
}
