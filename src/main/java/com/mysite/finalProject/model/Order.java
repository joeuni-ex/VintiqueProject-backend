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

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    private Integer totalPrice; // 총 금액

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> order_items = new ArrayList<>();

    @Column(name ="cart_item_count", nullable = false)
    private Integer cartItemCount;

    @Column(name ="createDate", nullable = false)
    private String createDate; // 날짜


    public void addOrderItem(OrderItem order_item){
        order_items.add(order_item);
        order_item.setOrder(this);
    }

    
    //주문 저장
    public static Order createOrder(User user, List<OrderItem> orderItemList ,int cartItemCount){
        //날짜 포맷 설정
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String createDateTime = simpleDateFormat.format(now);

        Order order = new Order();
        order.setUser(user);
        for(OrderItem order_item : orderItemList){
            order.addOrderItem(order_item);
        }
        order.setStatus("주문 완료");
        order.setCreateDate(createDateTime);
        order.setCartItemCount(cartItemCount);

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
