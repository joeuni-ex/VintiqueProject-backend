package com.mysite.finalProject.Service;

import com.mysite.finalProject.model.User;
import com.mysite.finalProject.security.UserPrinciple;
import com.mysite.finalProject.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    //필요한 객체를 생성자 주입 받음
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    //유저 네임과 패스워드로 로그인 후 토큰을 만들어 리턴한다.
    public User signInAndReturnJWT(User signInRequest){
        //스프링 시큐리티에서 로그인하기
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword())
        );

        UserPrinciple userPrinciple =(UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);// 로그인 된 유저 정보로 jwt토큰 만들기

        //유저 정보에 토큰을 추가해서 리턴한다.
        User signInUser = userPrinciple.getUser();
        signInUser.setToken(jwt);

        return signInUser;

    }
}
