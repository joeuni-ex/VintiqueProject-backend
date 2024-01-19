package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Cart;
import com.mysite.finalProject.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByCartIdAndProductId(Long cartId, long productId);

    List<CartItem> findAllByCart(Cart cart);
    void deleteByCartId(Long id);
}
