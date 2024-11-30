package com.BeeTech.Cartify.Service.Order;

import com.BeeTech.Cartify.Model.Order;

public interface OrderServiceInt {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);
}
