package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.CartCreateRequestDto;
import com.mysite.finalProject.dto.CartItemResponseDto;
import com.mysite.finalProject.model.Cart;
import com.mysite.finalProject.model.CartItem;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.User;

import java.util.List;

public interface CartService {

    void create(CartCreateRequestDto req, User user);

    List<CartItemResponseDto> findAll(User user);

    //장바구니 삭제하기
    void deleteById(Long id, User user);
}
