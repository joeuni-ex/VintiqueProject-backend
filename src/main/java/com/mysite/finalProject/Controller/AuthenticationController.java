package com.mysite.finalProject.Controller;


import com.mysite.finalProject.Service.AuthenticationService;
import com.mysite.finalProject.Service.UserService;
import com.mysite.finalProject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    //가입하기
    @PostMapping("sign-up")
    public ResponseEntity<Object> signUp(@RequestBody User user) {
        //같은 유저가 있는지 확인한다 있으면 CONFLICT(에러)으로 리턴
        if(userService.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //같은 유저가 없으면 유저를 저장하고  http 상태 성공을 리턴
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    //로그인
    @PostMapping("sign-in")
    public ResponseEntity<Object> signIn(@RequestBody User user){
        //인증 완료 시 jwt 토큰이 리턴됨 상태는 ok
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

}
