package com.mysite.finalProject.controller;

import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.UserRepository;
import com.mysite.finalProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping("api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserRepository userRepository;

    //제품 추가하기
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody PostProductRequestDto product){
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }


    //전체 제품 조회하기 (페이징 추가)
    @GetMapping
    public ResponseEntity<Object> getAllProducts(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //인증된 유저의 여부에 따라 관심 제품 가져오기
        //인증된 유저는 가져오지 않고, 인증된 유저만 가져온다.
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<>(productService.findAll(page, maxPageSize, user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.findAll(page, maxPageSize, null), HttpStatus.OK);
        }
    }

    //전체 제품 조회하기 (가격 높은 순  + 페이징)
    @GetMapping("/price-desc")
    public ResponseEntity<Object> getAllOrderByPriceDesc(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //인증된 유저의 여부에 따라 관심 제품 가져오기
        //인증된 유저는 가져오지 않고, 인증된 유저만 가져온다.
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<>(productService.findAllOrderByColumnDesc(page, maxPageSize, user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.findAllOrderByColumnDesc(page, maxPageSize, null), HttpStatus.OK);
        }
    }

    // 전체 제품 조회하기 ( 가격 낮은 순  + 페이징)
    @GetMapping("/price-asc")
    public ResponseEntity<Object> getAllOrderByPriceAsc(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //인증된 유저의 여부에 따라 관심 제품 가져오기
        //인증된 유저는 가져오지 않고, 인증된 유저만 가져온다.
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<>(productService.findAllOrderByColumnAsc(page, maxPageSize, user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.findAllOrderByColumnAsc(page, maxPageSize, null), HttpStatus.OK);
        }
    }
    
    
    // 카테고리 별 조회 (페이징)
    @GetMapping("/{category}/default")
    public ResponseEntity<Object> getCategory(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize,@PathVariable String category){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //인증된 유저의 여부에 따라 관심 제품 가져오기
        //인증된 유저는 가져오지 않고, 인증된 유저만 가져온다.
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<>(productService.findByCategory(page, maxPageSize, user,category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.findByCategory(page, maxPageSize, null,category), HttpStatus.OK);
        }

    }

    // 카테고리 별 조회 (가격 낮은 순)
    @GetMapping("/{category}/price-asc")
    public ResponseEntity<Object> getCategoryByOrderAsc(@PathVariable String category, @RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //인증된 유저의 여부에 따라 관심 제품 가져오기
        //인증된 유저는 가져오지 않고, 인증된 유저만 가져온다.
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<>(productService.findByCategoryByOrderAsc(page, maxPageSize, user,category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.findByCategoryByOrderAsc(page, maxPageSize, null,category), HttpStatus.OK);
        }
    }


    // 카테고리 별 조회 (가격 높은 순)
    @GetMapping("/{category}/price-desc")
    public ResponseEntity<Object> getCategoryByOrderDesc(@PathVariable String category,
                                            @RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize
                                            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //인증된 유저의 여부에 따라 관심 제품 가져오기
        //인증된 유저는 가져오지 않고, 인증된 유저만 가져온다.
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<>(productService.findByCategoryByOrderDesc(page, maxPageSize, user,category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.findByCategoryByOrderDesc(page, maxPageSize, null,category), HttpStatus.OK);
        }
    }


    //제품 상세 조회하기
    @GetMapping("{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable Long productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //인증된 유저의 여부에 따라 관심 제품 가져오기
        //인증된 유저는 가져오지 않고, 인증된 유저만 가져온다.
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            return new ResponseEntity<>(productService.findByIdProduct(productId,user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productService.findByIdProduct(productId,null), HttpStatus.OK);
        }
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
