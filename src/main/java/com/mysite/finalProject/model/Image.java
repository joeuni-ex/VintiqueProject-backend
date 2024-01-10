package com.mysite.finalProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "image")
@Table(name = "image")
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "image" ,nullable = false)
    private String image;

    public Image(Long productNumber, String image) {
        this.productId = productNumber;
        this.image =image;
    }
}
