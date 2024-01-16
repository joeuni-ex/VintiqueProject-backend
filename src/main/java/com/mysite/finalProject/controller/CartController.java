package com.mysite.finalProject.controller;


import com.mysite.finalProject.dto.CartCreateRequestDto;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.UserRepository;
import com.mysite.finalProject.security.UserPrinciple;
import com.mysite.finalProject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {


    private final CartService cartService;
    private final UserRepository userRepository;

    // 장바구니 담기
    @PostMapping
    public ResponseEntity<Object> create( @RequestBody CartCreateRequestDto req ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        cartService.create(req, user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    // 장바구니 조회
    @GetMapping
    public ResponseEntity<Object> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        System.out.println(user);

        return new ResponseEntity<>(cartService.findAll(user), HttpStatus.CREATED);
    }

    //장바구니 품목 삭제
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Object> deleteById(@PathVariable("cartItemId") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        cartService.deleteById(id, user);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
