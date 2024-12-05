package com.BeeTech.Cartify.Dto;

import java.math.BigDecimal;
import java.util.Set;

public record CartDto(
    Long cartId,
    Set<CartItemDto> items,
    BigDecimal totalAmount
) {
}
