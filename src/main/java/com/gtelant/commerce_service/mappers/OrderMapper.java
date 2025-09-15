package com.gtelant.commerce_service.mappers;

import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.ItemResponse;
import com.gtelant.commerce_service.dtos.OrderResponse;
import com.gtelant.commerce_service.dtos.OrderStatusResponse;
import com.gtelant.commerce_service.dtos.UserResponse;
import com.gtelant.commerce_service.models.Item;
import com.gtelant.commerce_service.models.Order;
import com.gtelant.commerce_service.models.OrderStatus;
import com.gtelant.commerce_service.models.User;

@Component
public class OrderMapper {

    private static class SimpleMapper {
        static UserResponse toUserResponse(User user) {
            if (user == null) return null;
            UserResponse dto = new UserResponse();
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            return dto;
        }
        static OrderStatusResponse toOrderStatusResponse(OrderStatus orderStatus) {
            if (orderStatus == null) return null;
            OrderStatusResponse dto = new OrderStatusResponse();
            dto.setId(orderStatus.getId());
            dto.setName(orderStatus.getName());
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
            dto.setOrderId(item.getOrder().getId());
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
        dto.setOrderStatus(SimpleMapper.toOrderStatusResponse(order.getOrderStatus()));
        dto.setItems(order.getItems().stream().map(SimpleMapper::toItemResponse).toList());
        return dto;
    }
}
