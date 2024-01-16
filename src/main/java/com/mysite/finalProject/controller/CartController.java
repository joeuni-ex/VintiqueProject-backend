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
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create( @RequestBody CartCreateRequestDto req ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        cartService.create(req, user);

        return ResponseEntity.ok(true);
    }

}
