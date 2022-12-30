package com.driver.service.impl;

import com.driver.Converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity=OrderEntity.builder().orderId(order.getOrderId()).cost(order.getCost()).items(order.getItems())
                .status(order.isStatus()).userId(order.getUserId()).build();
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null){
            System.out.println("Order entity doesn't exist");
            return null;
        }
        OrderDto orderDto=OrderDto.builder().id(orderEntity.getId()).orderId(orderEntity.getOrderId()).cost(orderEntity.getCost())
                .items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null){
            System.out.println("Order entity doesn't exist");
            return null;
        }
        OrderDto orderDto=order;
        orderDto.setId(orderEntity.getId());
        orderRepository.updateOrderEntity(order.getId(), order.getOrderId(), order.getItems(), order.getCost(), order.getUserId(), order.isStatus());
        return orderDto;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null) System.out.println("Order entity doesn't exist");
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> orderEntities= (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> orderDtos=new ArrayList<>();
        for(OrderEntity orderEntity:orderEntities) orderDtos.add(OrderConverter.convertEntityToDto(orderEntity));
        return orderDtos;
    }
}