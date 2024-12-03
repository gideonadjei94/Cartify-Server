package com.BeeTech.Cartify.Service.Order;

import com.BeeTech.Cartify.Dto.OrderDto;
import com.BeeTech.Cartify.Enums.OrderStatus;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Mappers.OrderMapper;
import com.BeeTech.Cartify.Model.Cart;
import com.BeeTech.Cartify.Model.Order;
import com.BeeTech.Cartify.Model.OrderItem;
import com.BeeTech.Cartify.Model.Product;
import com.BeeTech.Cartify.Repository.OrderRepository;
import com.BeeTech.Cartify.Repository.ProductRepository;
import com.BeeTech.Cartify.Service.Cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInt{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final OrderMapper orderMapper;


    @Transactional
    @Override
    public Order placeOrder(Long userId) {

        Cart cart =  cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return savedOrder;
    }

    private Order createOrder(Cart cart){
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart){
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice(),
                    order,
                    product
            );
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList){
        return orderItemList
                .stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity()))
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::apply)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId){
        return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper)
                .collect(Collectors.toList());
    }
}
