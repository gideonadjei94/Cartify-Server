package com.BeeTech.Cartify.Dto;


import com.BeeTech.Cartify.Enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDto(
        Long id,
        Long userId,
        LocalDate orderDate,
        BigDecimal totalAmount,
        OrderStatus status,
        List<OrderItemDto> items
) {
}
