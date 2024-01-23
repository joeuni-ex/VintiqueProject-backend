package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.OrderResponseDto;
import com.mysite.finalProject.dto.OrderViewResponseDto;
import com.mysite.finalProject.model.*;
import com.mysite.finalProject.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ReviewRepository reviewRepository;



    @Override
    public void createOrder(User user){
        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);
    }

    //주문 하기
    @Override
    public Long order(User user){
        List<OrderItem> order_items = new ArrayList<>(); // 주문내역에 추가할 아이템리스트

        Cart cart = cartRepository.findByUserId(user.getId()); // 유저의 id로 카트 검색

        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart); //해당 카트안에 있는 아이템 목록 가져옴

        int cartItemCount = 0; // 카트 안의 목록 개수

        for(CartItem cart_item : cartItems){
            OrderItem order_item = OrderItem.createOrderItem(cart_item.getProduct(),cart_item.getQuantity());
            order_items.add(order_item);
            cartItemCount = cartItemCount + 1;
        }

        Order order = Order.createOrder(user,order_items,cartItemCount); //카트 안의 개수
        order.setTotalPrice(order.getTotalPrice());//총 금액
        orderRepository.save(order);

        cartItemRepository.deleteByCartId(cart.getId()); //해당 유저의 카트안의 상품 삭제
        cartRepository.deleteById(cart.getId());// 해당 카트 삭제

        return  order.getId();
    }


    //전체 주문 내역 조회(페이징 추가)
    @Override
    public Page<OrderResponseDto> getAllOrders(int page, int maxPageSize) {
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        Page<Order> orders = orderRepository.findAll(pageable);

        return orders.map(OrderResponseDto::toDto);
    }


    //유저별 주문 내역 조회(페이징 추가)
    @Override
    public Page<OrderResponseDto> getUserOrder(int page, int maxPageSize,User user) {
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        Page<Order> orders = orderRepository.findByUserId(user.getId(),pageable);

        return orders.map(OrderResponseDto::toDto);
    }

    //유저 주문 상세 내용
    @Override
    public List<OrderViewResponseDto> orderView(Long id){
       List<OrderItem>  items = orderItemRepository.findByOrderId(id);

       List<OrderViewResponseDto> result = new ArrayList<>();

       for(OrderItem item: items){
           Product product = item.getProduct();
           Boolean review = reviewRepository.existsByOrderItemId(item.getId());
           result.add(new OrderViewResponseDto().toDto(item,product.getId(),  product.getName(), product.getPrice(), product.getMainImage(),review));
       }

        return result;
    }



    //수정
    @Override
    public void orderUpdate(Long id, Order order) {
        Order tempOrder = orderRepository.findById(id).get();
        tempOrder.setStatus(order.getStatus());

        orderRepository.save(tempOrder);
    }

}
