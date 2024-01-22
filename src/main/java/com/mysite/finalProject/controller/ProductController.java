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
//
//        for(int i =1;i<=20; i++){
//            productService.saveProduct(product);
//        }
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }


    //전체 제품 조회하기 (페이징 추가)
    @GetMapping
    public ResponseEntity<Object> getAllProducts(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        return new ResponseEntity<>(productService.findAll(page,maxPageSize), HttpStatus.OK);
    }

    //전체 제품 조회하기 (가격 높은 순 )
    @GetMapping("/price-desc")
    public ResponseEntity<Object> getAllOrderByPriceDesc(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        return new ResponseEntity<>(productService.findAllOrderByColumnDesc(page,maxPageSize), HttpStatus.OK);
    }

    // 전체 제품 조회하기 ( 가격 낮은 순 )
    @GetMapping("/price-asc")
    public ResponseEntity<Object> getAllOrderByPriceAsc(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        return new ResponseEntity<>(productService.findAllOrderByColumnAsc(page,maxPageSize), HttpStatus.OK);
    }



    //제품 상세 조회하기
    @GetMapping("{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable Long productId){
        return new ResponseEntity<>(productService.findByIdProduct(productId), HttpStatus.OK);
    }

    
    //제품 수정하기
    @PutMapping("{productId}")
    public ResponseEntity<Object> modifyProduct(@PathVariable Long productId, @RequestBody PostProductRequestDto product){
        return new ResponseEntity<>(productService.modifyProduct(productId,product), HttpStatus.CREATED);
    }

    //제품 삭제하기
    @DeleteMapping("{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable  Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
