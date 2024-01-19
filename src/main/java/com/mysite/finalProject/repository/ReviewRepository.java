package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
