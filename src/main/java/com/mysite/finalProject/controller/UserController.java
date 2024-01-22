package com.mysite.finalProject.controller;

import com.mysite.finalProject.model.Cart;
import com.mysite.finalProject.model.CartItem;
import com.mysite.finalProject.service.CartService;
import com.mysite.finalProject.service.UserService;
import com.mysite.finalProject.model.Role;
import com.mysite.finalProject.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CartService cartService;

    //유저 중복 검사
    @GetMapping("/checkuser")
    public ResponseEntity<Object> searchUser(@RequestParam String username) {
        if (userService.findByUsername(username).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //유저의 권한 업데이트
    @PutMapping("change/{role}")//role-> 주소 변수로 들어감 @PathVariable로 받음
    public ResponseEntity<Object> changeRole(@AuthenticationPrincipal UserPrinciple userPrinciple
            , @PathVariable Role role) {
        userService.changeRole(role, userPrinciple.getUsername());

        return ResponseEntity.ok(true);
    }

}