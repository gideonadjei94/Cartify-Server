package com.BeeTech.Cartify.Dto;

import java.math.BigDecimal;

public record OrderItemDto(
        Long productId,
        String productName,
        int quantity,
        BigDecimal price
) {
}
