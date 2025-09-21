package com.gtelant.commerce_service.mappers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.ItemRequest;
import com.gtelant.commerce_service.dtos.ItemResponse;
import com.gtelant.commerce_service.dtos.OrderRequest;
import com.gtelant.commerce_service.dtos.OrderResponse;
import com.gtelant.commerce_service.dtos.UserResponse;
import com.gtelant.commerce_service.enums.OrderStatus;
import com.gtelant.commerce_service.models.Item;
import com.gtelant.commerce_service.models.Order;
import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.services.ItemService;
import com.gtelant.commerce_service.services.ProductService;
import com.gtelant.commerce_service.services.UserService;

@Component
public class OrderMapper {

    private final UserService userService;
    private final ProductService productService;
    private final ItemService itemService;

    public OrderMapper(UserService userService, ProductService productService, ItemService itemService){
        this.userService = userService;
        this.productService = productService;
        this.itemService = itemService;
    }

    private static class SimpleMapper {
        static UserResponse toUserResponse(User user) {
            if (user == null) return null;
            UserResponse dto = new UserResponse();
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            return dto;
        }
        static ItemResponse toItemResponse(Item item) {
            if (item == null) return null;
            ItemResponse dto = new ItemResponse();
            dto.setId(item.getId());
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getReference());
            dto.setAmount(item.getAmount());
            dto.setPrice(item.getPrice());
            dto.setOrderReference(item.getOrderReference());
            return dto;
        }
    }

    public OrderResponse toResponse(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setReference(order.getReference());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setDelivery(order.getDelivery());
        dto.setTax(order.getTax());
        dto.setReturned(order.isReturned());
        dto.setUser(SimpleMapper.toUserResponse(order.getUser()));
        dto.setStatus(order.getStatus());
        dto.setItems(itemService.getAllItemsByOrderReference(order.getReference()).stream()
                .map(SimpleMapper::toItemResponse).toList());
        return dto;
    }

    public Order toEntity(OrderRequest request) {
        Order dto = new Order();
        dto.setStatus(OrderStatus.NULL); // Default status
        dto.setShippingAddress(request.getShippingAddress());
        dto.setDelivery(request.getDelivery());
        dto.setTax(request.getTax());
        dto.setReturned(false); // Default to not returned
        Optional<User> user = userService.getUserById(request.getUserId());
        if (user.isPresent()){
            dto.setUser(user.get());
        }
        return dto;
    }

    public Item toItemEntity(ItemRequest request, String orderReference) {
        Item dto = new Item();
        dto.setOrderReference(orderReference);
        dto.setAmount(request.getAmount());
        Optional<Product> product = productService.getProductById(request.getProductId());
        if (product.isPresent()){
            dto.setProduct(product.get());
            dto.setPrice(product.get().getPrice());
        }
        return dto;
    }
}
