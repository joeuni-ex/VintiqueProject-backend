package com.mysite.finalProject.Service;

import com.mysite.finalProject.model.User;

public interface AuthenticationService {
    //유저 네임과 패스워드로 로그인 후 토큰을 만들어 리턴한다.
    User signInAndReturnJWT(User signInRequest);
}
