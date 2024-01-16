package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByCartIdAndProductId(Long cartId, long productId);
}
