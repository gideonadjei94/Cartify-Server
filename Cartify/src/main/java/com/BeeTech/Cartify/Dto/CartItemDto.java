package com.BeeTech.Cartify.Dto;

import java.math.BigDecimal;

public record CartItemDto(
        Long itemId,
        Integer quantity,
        BigDecimal unitPrice,
        ProductDto product
) {
}
