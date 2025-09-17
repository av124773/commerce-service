package com.gtelant.commerce_service.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtelant.commerce_service.dtos.OrderRequest;
import com.gtelant.commerce_service.dtos.OrderResponse;
import com.gtelant.commerce_service.mappers.OrderMapper;
import com.gtelant.commerce_service.models.Item;
import com.gtelant.commerce_service.models.Order;
import com.gtelant.commerce_service.services.ItemService;
import com.gtelant.commerce_service.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ItemService itemService;

    public OrderController(OrderService orderService, OrderMapper orderMapper, ItemService itemService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.itemService = itemService;
    }
    
    @Operation(summary = "取得所有Orders列表", description = "")
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders().stream().map(orderMapper::toResponse).toList();
    }

    @Operation(summary = "取得指定Order", description = "")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(int id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(orderMapper.toResponse(order.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "建立新的Order", description = "")
    @PostMapping()
    public ResponseEntity<OrderResponse> postMethodName(@RequestBody OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        // 產生隨機不重複的訂單編號
        order.setReference(RandomStringUtils.secure().nextAlphanumeric(6).toUpperCase());
        // 先產生 order 才能把 orderReference 放入 item
        List<Item> items = request.getItems().stream()
                .map((item) -> orderMapper.toItemEntity(item, order.getReference()))
                .toList();

        Order savedOrder = orderService.saveOrder(order);
        itemService.saveAllItems(items);

        OrderResponse response = orderMapper.toResponse(savedOrder);
        return ResponseEntity.ok(response);
    }
    

}
