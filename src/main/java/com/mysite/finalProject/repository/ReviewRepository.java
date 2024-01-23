package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Interest;
import com.mysite.finalProject.model.Review;
import com.mysite.finalProject.repository.projection.ReviewItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProductId(Long productId);

    @Query("select " +
            "r.id as id," +
            "r.reviewContent as reviewContent, " +
            "r.rate as rate, " +
            "r.createTime as createTime, " +
            "u.id as userId, " +
            "u.name as name " +
            "from Review r left join User u on r.userId = u.id " +
            "where r.productId = :productId" )
    List<ReviewItem> findByReviewProductId(@Param("productId") Long productId);


    //리뷰 작성자 찾기
    @Query("select " +
            "u.username as username " +
            "from Review r left join User u on r.userId = u.id " +
            "where r.id = :reviewId" )
    String findUsernameById(@Param("reviewId") Long reviewId);

    //유저 별 리뷰 목록
    @Query("select " +
            "r.id as id," +
            "r.reviewContent as reviewContent, " +
            "r.rate as rate, " +
            "r.createTime as createTime, " +
            "u.id as userId, " +
            "u.name as name " +
            "from Review r left join User u on r.userId = u.id " +
            "where r.userId = :userId" )
    Page<ReviewItem> findReviewByUserId(Pageable pageable,@Param("userId") Long userId);


    Page<Review> findByUserId(Long userId, Pageable pageable);
}
