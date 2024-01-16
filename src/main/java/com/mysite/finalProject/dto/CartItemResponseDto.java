package com.mysite.finalProject.dto;


import com.mysite.finalProject.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDto {

    private Long cartItemId;
    private String name;
    private Integer InsertQuantity;
    private Integer price;
    private String mainImage;
    public static CartItemResponseDto toDto(CartItem cartItem, String name, int price ,String mainImage) {
        return new CartItemResponseDto(cartItem.getId(), name, cartItem.getQuantity(), price, mainImage);
    }
}
