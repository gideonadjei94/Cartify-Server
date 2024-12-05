package com.BeeTech.Cartify.Mappers;

import com.BeeTech.Cartify.Dto.*;
import com.BeeTech.Cartify.Model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                 new CartDto(
                        user.getCart().getId(),
                        user.getCart().getItems()
                                .stream()
                                .map(item -> new CartItemDto(
                                        item.getId(),
                                        item.getQuantity(),
                                        item.getUnitPrice(),
                                         new ProductDto(
                                                item.getProduct().getId(),
                                                item.getProduct().getName(),
                                                item.getProduct().getBrand(),
                                                item.getProduct().getPrice(),
                                                item.getProduct().getInventory(),
                                                item.getProduct().getDescription(),
                                                item.getProduct().getCategory(),
                                                item.getProduct().getImages()
                                                        .stream()
                                                        .map(image -> new ImageDto(
                                                                image.getId(),
                                                                image.getFileName(),
                                                                image.getDownloadUrl()
                                                        )).collect(Collectors.toList())
                                        )

                        )).collect(Collectors.toSet()),
                        user.getCart().getTotalAmount()
                ),
                user.getOrders()
                        .stream()
                        .map(order -> new OrderDto(
                                order.getOrderId(),
                                order.getUser().getId(),
                                order.getOrderDate(),
                                order.getTotalAmount(),
                                order.getOrderStatus(),
                                order.getOrderItems()
                                        .stream()
                                        .map(item -> new OrderItemDto(
                                                item.getProduct().getId(),
                                                item.getProduct().getName(),
                                                item.getQuantity(),
                                                item.getPrice()
                                        )).collect(Collectors.toList())
                        )).collect(Collectors.toList())
        );
    }
}
