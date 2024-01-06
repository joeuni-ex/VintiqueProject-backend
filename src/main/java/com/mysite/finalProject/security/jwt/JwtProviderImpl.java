package com.mysite.finalProject.security.jwt;

import com.mysite.finalProject.security.UserPrinciple;
import com.mysite.finalProject.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProviderImpl implements JwtProvider{
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;


    @Override
    public String generateToken(UserPrinciple auth){
        //토큰 만드는 함수
        String authorites = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder() // 토큰은 문자열로 리턴됨
                .setSubject(auth.getUsername())
                .claim("roles", authorites)
                .claim("userId", auth.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))//시간 : 혀재 시간에서 하루 유지
                .signWith(key, SignatureAlgorithm.HS512) //암호화 된 키
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request){
        //유저 정보 부분만 가져옴
        Claims claims = extracClaims(request);
        //유저 정보가 없으면 종료
        if(claims == null) return null;
        //유저 이름을 가져온다.
        String username = claims.getSubject();
        //유저 id를 가져온다.
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());
        //유저 정보들을 담는다.
        UserDetails userDetails = UserPrinciple.builder()
                .username((username))
                .authorities(authorities)
                .id(userId)
                .build();
        //유저 이름이 없으면 null 리턴
        if(username == null) return null;
        //스프링 시큐리티 인증
        return new UsernamePasswordAuthenticationToken(userDetails,null,authorities);
    }

    //토큰의 유효성 체크 (유저 정보와 날짜 체크)
    @Override
    public boolean isTokenValid(HttpServletRequest request){
        Claims claims = extracClaims(request);

        //토큰에 claims 데이터가 없으면 사용불가 false 리턴
        if(claims == null) return false;
        //토큰사용기간 만료시에도 사용불가 false 리턴
        if (claims.getExpiration().before(new Date())) return false;

        return true;
    }

    @Override
    //JwtProviderImpl 리퀘스트의 토큰에서 암호풀어 Claims 가져오기
    //토큰에서 유저 정보 CLAIM 부분만 가져옴
    public Claims extracClaims(HttpServletRequest request){
        //리퀘스트 헤더에서 토큰만 가져옴
        String token = SecurityUtils.extractAuthTokenFromRequest(request);
        //만약 토큰이 없다면  null을 리턴한다.
        if(token == null)return null;
        //시크릿 키 암호를 풀기 (hmacShaKeyFor를 사용)
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        //유저의 정보 부분을 리턴한다.
        return  Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
