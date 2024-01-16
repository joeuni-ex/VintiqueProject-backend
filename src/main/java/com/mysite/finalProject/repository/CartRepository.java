package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Cart;
import com.mysite.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart,Long> {

    Cart findByUserId(Long userId);


    Cart findCartByUser(User user);
}
