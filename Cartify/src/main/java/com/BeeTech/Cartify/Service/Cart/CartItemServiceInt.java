package com.BeeTech.Cartify.Service.Cart;

import com.BeeTech.Cartify.Model.CartItem;

public interface CartItemServiceInt {
    void addCartItem(Long cartId, Long productId, int quantity);
    void removeCartItem(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
