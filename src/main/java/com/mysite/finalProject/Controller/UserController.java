package com.mysite.finalProject.Controller;

import com.mysite.finalProject.Service.UserService;
import com.mysite.finalProject.model.Role;
import com.mysite.finalProject.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //유저의 권한 업데이트
    @PutMapping("change/{role}")//role-> 주소 변수로 들어감 @PathVariable로 받음
    public ResponseEntity<Object> changeRole(@AuthenticationPrincipal UserPrinciple userPrinciple
            ,@PathVariable Role role){
        userService.changeRole(role,userPrinciple.getUsername() );

        return ResponseEntity.ok(true);
    }

}
