package com.BeeTech.Cartify.Service.Cart;

import com.BeeTech.Cartify.Model.Cart;

import java.math.BigDecimal;

public interface CartServiceInt {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();
}
