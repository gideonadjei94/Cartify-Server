package com.BeeTech.Cartify.Service.Cart;

import com.BeeTech.Cartify.Dto.UserDto;
import com.BeeTech.Cartify.Model.Cart;
import com.BeeTech.Cartify.Model.User;

import java.math.BigDecimal;

public interface CartServiceInt {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
