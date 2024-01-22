package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Cart;
import com.mysite.finalProject.model.Interest;
import com.mysite.finalProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest,Long> {

  //유저 별 관심 제품 조회
  Page<Interest> findByUserId(Long userId, Pageable pageable);

    void deleteByUserIdAndProductId(Long userId,Long productId);
}
