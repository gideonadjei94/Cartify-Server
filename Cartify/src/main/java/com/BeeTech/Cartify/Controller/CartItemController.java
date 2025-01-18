package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Dto.UserDto;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Model.Cart;
import com.BeeTech.Cartify.Model.User;
import com.BeeTech.Cartify.Response.ApiResponse;
import com.BeeTech.Cartify.Service.Cart.CartItemServiceInt;
import com.BeeTech.Cartify.Service.Cart.CartServiceInt;
import com.BeeTech.Cartify.Service.User.UserService;
import com.BeeTech.Cartify.Service.User.UserServiceInt;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cartItems")
public class CartItemController {
    private final CartItemServiceInt cartItemServiceInt;
    private final CartServiceInt cartServiceInt;
    private final UserServiceInt userServiceInt;

    @PostMapping("/add/item")
    public ResponseEntity<ApiResponse> addItemToCart(
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity){
        try {
            User user = userServiceInt.getAuthenticatedUser();
            Cart cart = cartServiceInt.initializeNewCart(user);

            cartItemServiceInt.addCartItem(cart.getId(), productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item successfully added", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }catch (JwtException e){
            return ResponseEntity
                    .status(UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("delete/item/{productId}/from-cart/{cartId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId){
        try {
            cartItemServiceInt.removeCartItem(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Item removed successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("update/item/{productId}/{cartId}")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                          @PathVariable Long productId,
                                                          @RequestParam Integer quantity){
        try {
            cartItemServiceInt.updateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item Quantity successfully Updated", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
