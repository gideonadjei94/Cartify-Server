package com.BeeTech.Cartify.Service.Order;

import com.BeeTech.Cartify.Dto.OrderDto;
import com.BeeTech.Cartify.Model.Order;

import java.util.List;

public interface OrderServiceInt {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
