package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Dto.OrderDto;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Model.Order;
import com.BeeTech.Cartify.Response.ApiResponse;
import com.BeeTech.Cartify.Service.Order.OrderServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderServiceInt orderServiceInt;

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId){
        try {
            Order order = orderServiceInt.placeOrder(userId);
            return ResponseEntity.ok(new ApiResponse("Order Placed Successfully", order));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId){
        try {
            OrderDto order = orderServiceInt.getOrder(orderId);
            return ResponseEntity.ok(new ApiResponse("Order Fetched Successfully", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/order")
    public ResponseEntity<ApiResponse> getUserOrders(@RequestParam Long userId){
        try {
            List<OrderDto> order = orderServiceInt.getUserOrders(userId);
            return ResponseEntity.ok(new ApiResponse("Order Fetched Successfully", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }



}
