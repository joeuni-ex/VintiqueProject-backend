package com.mysite.finalProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Data
@Entity
@Table(name = "interest")
@NoArgsConstructor
@AllArgsConstructor
public class Interest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)//It is only for foreign key.
    private User user;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;


    @Column(name = "create_time", nullable = false)
    private String createTime;



    //관심 제품 저장
    public static Interest addInterest(User user, Product product , Long productId){
        //날짜 포맷 설정
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String createDateTime = simpleDateFormat.format(now);


        Interest interest = new Interest();

        interest.setUserId(user.getId());
        interest.setProduct(product);
        interest.setProductId(productId);
        interest.setCreateTime(createDateTime);
        return interest;
    }

}
