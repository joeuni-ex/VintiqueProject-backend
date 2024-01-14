package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Modifying
    @Query("delete " +
            "from image " +
            "where productId = :productId")
    void deleteByProductId(@Param("productId") Long productId);
}
