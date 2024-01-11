package com.mysite.finalProject.model;

import com.mysite.finalProject.dto.PostProductRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


@Data
@Entity(name = "product")
@Table(name = "product")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category" ,nullable = false)
    private String category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name ="favorite_count", nullable = false)
    @ColumnDefault("0")
    private Integer favoriteCount;

    @Column(name ="review_count", nullable = false)
    @ColumnDefault("0")
    private Integer reviewCount;

    @Column(name = "create_time", nullable = false)
    private String createTime;



    public Product(PostProductRequestDto dto){

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String createDateTime = simpleDateFormat.format(now);
        this.id= 1L;
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.category = dto.getCategory();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
        this.favoriteCount = 0;
        this.reviewCount =0 ;
        this.createTime = createDateTime;

    }

}
