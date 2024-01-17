package com.mysite.finalProject.controller;


import com.mysite.finalProject.model.Purchase;
import com.mysite.finalProject.security.UserPrinciple;
import com.mysite.finalProject.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("api/purchase")
@RequiredArgsConstructor
public class PurchaseController {


    private final PurchaseService purchaseService;

    // 구매 저장하기
    @PostMapping
    public ResponseEntity<Object> savePurchase(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.OK);
    }

    // 유저별 구매 목록 가져오기
    @GetMapping
    public ResponseEntity<Object> getAllPurchaseOfUser(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize,@AuthenticationPrincipal UserPrinciple userPrinciple){
        return ResponseEntity.ok(purchaseService.findPurchaseItemsOfUser(page,maxPageSize,userPrinciple.getId()));
    }



    // 전체 구매 목록 가져오기(관리자용)
    @GetMapping("/all")
    public ResponseEntity<Object> getAllPurchase(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        return ResponseEntity.ok(purchaseService.findAllPurchase(page,maxPageSize));
    }
}
