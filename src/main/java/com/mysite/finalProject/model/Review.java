package com.mysite.finalProject.model;

import com.mysite.finalProject.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    //테스트
    @Column(name = "orderItem_id", nullable = false)
    private Long orderItemId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderItem_id", referencedColumnName = "id", insertable = false, updatable = false)
    private OrderItem orderItem;

    @Column(name = "create_time", nullable = false)
    private String createTime;


    //리뷰 저장
    public static Review createReview(User user, ReviewRequestDto dto){
        //날짜 포맷 설정
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDateTime = simpleDateFormat.format(now);

        Review review = new Review();

        review.setUser(user);
        review.setUserId(user.getId());
        review.setReviewContent(dto.getReviewContent());
        review.setRate(dto.getRate());
        review.setProductId(dto.getProductId());
        review.setOrderItemId(dto.getOrderItemId());
        review.setCreateTime(createDateTime);
        return review;
    }



    //리뷰 수정
    public static Review modifyReview(User user, ReviewRequestDto dto){
        //날짜 포맷 설정
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String createDateTime = simpleDateFormat.format(now);

        Review review = new Review();

        review.setUser(user);
        review.setUserId(user.getId());
        review.setReviewContent(dto.getReviewContent());
        review.setRate(dto.getRate());
        review.setProductId(dto.getProductId());
        review.setCreateTime(createDateTime);
        return review;
    }
}
