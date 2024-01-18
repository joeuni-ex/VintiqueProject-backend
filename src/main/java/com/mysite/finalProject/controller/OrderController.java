package com.mysite.finalProject.controller;


import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.UserRepository;
import com.mysite.finalProject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final UserRepository userRepository;
    private final OrderService orderService;

    //전체 주문 내역 가져오기
    @GetMapping("/all")
    public ResponseEntity<Object> myOrderPage(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        return new ResponseEntity<>( orderService.getAllOrders(page,maxPageSize),HttpStatus.OK);
    }

    //주문 하기
    @PostMapping
    public ResponseEntity<Object> addOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        orderService.order(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
