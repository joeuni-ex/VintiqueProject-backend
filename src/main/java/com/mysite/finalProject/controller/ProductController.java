package com.mysite.finalProject.controller;

import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //제품 추가하기
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody PostProductRequestDto product){
//        System.out.println(product.getBoardImageList());
//        System.out.println(product.getBoardImageList());

        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    //전체 제품 조회하기
    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    //제품 삭제하기
    @DeleteMapping("{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable  Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
