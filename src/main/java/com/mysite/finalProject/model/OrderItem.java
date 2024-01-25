package com.mysite.finalProject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="quantity", nullable = false)
    private Integer quantity; // 개수

    @Column(name ="price", nullable = false)
    private Integer price; // 금액

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order; // 주문 연결

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product; // 제품 연결


    public static OrderItem createOrderItem(Product product, int quantity){

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice());

        return orderItem;
    }
}
