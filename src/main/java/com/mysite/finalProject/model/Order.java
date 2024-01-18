package com.mysite.finalProject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="status", nullable = false)
    private String status; // 상태

    @Column(name ="total_price", nullable = false)
    private int totalPrice; // 총 금액

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> order_items = new ArrayList<>();

    @Column(name ="createDate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createDate; // 날짜


    public void addOrderItem(OrderItem order_item){
        order_items.add(order_item);
        order_item.setOrder(this);
    }

    public static Order createOrder(User user, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setUser(user);
        for(OrderItem order_item : orderItemList){
            order.addOrderItem(order_item);
        }
        order.setStatus("주문 완료");
        order.setCreateDate(LocalDateTime.now());
        return order;
    }


    //주문 전체 금액 산정
    public int getTotalPrice(){
        int totalPrice = 0;

        for(OrderItem order_item : order_items){
            totalPrice += (order_item.getPrice() * order_item.getQuantity());
        }

        return totalPrice;
    }
}
