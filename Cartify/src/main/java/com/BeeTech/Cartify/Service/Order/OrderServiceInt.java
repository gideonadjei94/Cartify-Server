package com.BeeTech.Cartify.Service.Order;

import com.BeeTech.Cartify.Model.Order;

import java.util.List;

public interface OrderServiceInt {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
