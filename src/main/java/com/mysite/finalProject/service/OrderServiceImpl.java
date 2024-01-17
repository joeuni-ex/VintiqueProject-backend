package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.OrderResponseDto;
import com.mysite.finalProject.model.*;
import com.mysite.finalProject.repository.CartItemRepository;
import com.mysite.finalProject.repository.CartRepository;
import com.mysite.finalProject.repository.OrderItemRepository;
import com.mysite.finalProject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;



    @Override
    public void createOrder(User user){
        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);
    }

    //주문 하기
    @Override
    public void order(User user){
        List<OrderItem> order_items = new ArrayList<>(); // 주문내역에 추가할 아이템리스트

        Cart cart = cartRepository.findByUserId(user.getId());

        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);


        for(CartItem cart_item : cartItems){
            OrderItem order_item = OrderItem.createOrderItem(cart_item.getProduct(),cart_item.getQuantity());
            order_items.add(order_item);
        }

        Order order = Order.createOrder(user,order_items);
        order.setTotalPrice(order.getTotalPrice());
        System.out.println(order.getTotalPrice());
        orderRepository.save(order);
    }


    //전체 주문 내역 조회
    @Override
    public List<OrderResponseDto> findAll() {
        //Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할
        System.out.println("아이템출력:");

        List<Order> items = orderRepository.findAll();
        System.out.println("아이템출력:"+items);
        List<OrderResponseDto> result = new ArrayList<>();

        for(Order item : items) {
            result.add(new OrderResponseDto().toDto(item));
        }
        return result;
    }


    //유저 별 주문 내역 조회


    @Override
    public Order orderView(Long id){
        return orderRepository.findById(id).get();
    }

    @Override
    public void orderUpdate(Long id, Order order){
        Order tempOrder = orderRepository.findById(id).get();
        tempOrder.setStatus(order.getStatus());

        orderRepository.save(tempOrder);
    }

}
