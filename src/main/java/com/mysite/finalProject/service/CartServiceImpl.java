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
        Product product = productRepository.findById(req.getProductId()).orElseThrow();

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

        List<CartItem> items = cartItemRepository.findAllByCart(cart);
        List<CartItemResponseDto> result = new ArrayList<>();

        for(CartItem item : items) {
            Product product = item.getProduct();
            result.add(new CartItemResponseDto().toDto(item, product.getName(), product.getPrice(),product.getMainImage()));
        }
        return result;
    }


    @Override
    //장바구니 삭제하기
    public void deleteById(Long id, User user) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow();
        Cart cart = cartItem.getCart();

        if (!cart.getUser().equals(user)) {
            System.out.println( "오류발생");
        }

        cartItemRepository.delete(cartItem);
    }
}
