package com.driver.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {
    public static OrderDto convertEntityToDto(OrderEntity orderEntity){
        return OrderDto.builder().id(orderEntity.getId()).orderId(orderEntity.getOrderId()).cost(orderEntity.getCost())
                .items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
    }
}
