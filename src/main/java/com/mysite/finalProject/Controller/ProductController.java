package com.mysite.finalProject.Controller;

import com.mysite.finalProject.Service.ProductService;
import com.mysite.finalProject.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //제품 추가하기
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    //전체 제품 조회하기
    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(productService.findeAllProducts(), HttpStatus.OK);
    }

    //제품 삭제하기
    @DeleteMapping("{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable  Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
