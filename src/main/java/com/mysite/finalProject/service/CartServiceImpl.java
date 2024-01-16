package com.mysite.finalProject.service;


import com.mysite.finalProject.dto.CartCreateRequestDto;
import com.mysite.finalProject.dto.CartItemResponseDto;
import com.mysite.finalProject.model.Cart;
import com.mysite.finalProject.model.CartItem;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.CartItemRepository;
import com.mysite.finalProject.repository.CartRepository;
import com.mysite.finalProject.repository.ProductRepository;
import com.mysite.finalProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;



    @Override
    public void create(CartCreateRequestDto req, User user) {
        Product product = productRepository.findById(req.getProduct_id()).orElseThrow();

//        if (product.getQuantity() < req.getQuantity()) {
//            throw new LakingOfProductQuantity();
//        }

        // 3. 장바구니 만들어줘야한다 사용자한테

        if (cartRepository.findByUserId(user.getId()) == null) {
            // 장바구니가 없다면 생성
            Cart cart = new Cart(user);
            cartRepository.save(cart);
        }

        Cart cart = cartRepository.findByUserId(user.getId());

        CartItem cartItem = new CartItem(cart, product, req.getQuantity());
        cartItemRepository.save(cartItem);
    }



    // 유저 별 장바구니 조회하기
    @Override
    public List<CartItemResponseDto> findAll(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        System.out.println(cart);

        List<CartItem> items = cartItemRepository.findAllByCart(cart);
        List<CartItemResponseDto> result = new ArrayList<>();

        for(CartItem item : items) {
            Product product = item.getProduct();
            result.add(new CartItemResponseDto().toDto(item, product.getName(), product.getPrice()));
        }
        System.out.println(result);
        return result;
    }


}
