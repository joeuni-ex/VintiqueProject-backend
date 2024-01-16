package com.mysite.finalProject.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCreateRequestDto {


    @NotNull(message = "상품 번호를 입력하세요.")
    private Long product_id;

    @NotNull(message = "구매 수량을 입력하세요.")
    private Integer quantity;
}
