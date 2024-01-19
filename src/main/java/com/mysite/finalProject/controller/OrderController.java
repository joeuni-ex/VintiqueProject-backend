package com.mysite.finalProject.controller;


import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.model.Order;
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
    public ResponseEntity<Object> allOrder(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        return new ResponseEntity<>( orderService.getAllOrders(page,maxPageSize),HttpStatus.OK);
    }

    //주문 하기
    @PostMapping
    public ResponseEntity<Object> addOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        return new ResponseEntity<>( orderService.order(user),HttpStatus.CREATED);
    }

    //유저별 주문 내역 가져오기
    @GetMapping()
    public ResponseEntity<Object> myOrderPage(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        return new ResponseEntity<>( orderService.getUserOrder(page,maxPageSize,user),HttpStatus.OK);
    }


    //주문 완료 -> 주문 상세 조회
    @GetMapping("/success/{id}")
    public ResponseEntity<Object> myOrderView(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        return new ResponseEntity<>( orderService.orderView(id),HttpStatus.CREATED);
    }



    //주문 상태 변경
    @PutMapping("/change/status/{id}")
    public ResponseEntity<Object> changeOrderStatus(@PathVariable Long id, @RequestBody Order order) {
        orderService.orderUpdate(id,order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
