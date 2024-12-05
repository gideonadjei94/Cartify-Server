package com.BeeTech.Cartify.Mappers;

import com.BeeTech.Cartify.Dto.OrderDto;
import com.BeeTech.Cartify.Dto.OrderItemDto;
import com.BeeTech.Cartify.Model.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderMapper implements Function<Order, OrderDto> {
    @Override
    public OrderDto apply(Order order){
        return new OrderDto(
                order.getOrderId(),
                order.getUser().getId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getOrderItems()
                        .stream()
                        .map(item -> new OrderItemDto(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                )).collect(Collectors.toList())
               );
    }

}
