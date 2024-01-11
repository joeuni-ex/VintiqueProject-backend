package com.mysite.finalProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostProductRequestDto {

    private String name;
    private String description;
    private String category;
    private Integer price;
    private Integer stock;
    private String mainImage;
    private List<String> boardImageList;


}
