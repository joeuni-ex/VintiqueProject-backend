package com.mysite.finalProject.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostProductRequestDto {

    private String name;
    private String description;
    private String category;
    private Integer price;
    private List<String> boardImageList;


}
