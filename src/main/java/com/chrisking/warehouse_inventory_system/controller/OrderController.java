package com.chrisking.warehouse_inventory_system.controller;

import com.chrisking.warehouse_inventory_system.dto.OrderRequest;
import com.chrisking.warehouse_inventory_system.entity.Order;
import com.chrisking.warehouse_inventory_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            Order savedOrder = orderService.createOrder(orderRequest);
            return ResponseEntity.ok(savedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-to-priority-tree")
    public ResponseEntity<String> addOrdersToPriorityTree() {
        return ResponseEntity.ok(orderService.addOrdersToPriorityTree());
    }

    @GetMapping("/priority/inorder")
    public ResponseEntity<List<Order>> getOrdersInPriorityOrder() {
        return ResponseEntity.ok(orderService.getOrdersInPriorityOrder());
    }

    @GetMapping("/priority/highest")
    public ResponseEntity<?> getHighestPriorityOrder() {
        Order order = orderService.getHighestPriorityOrder();

        if (order == null) {
            return ResponseEntity.badRequest().body("Priority tree is empty.");
        }

        return ResponseEntity.ok(order);
    }

    @GetMapping("/priority/lowest")
    public ResponseEntity<?> getLowestPriorityOrder() {
        Order order = orderService.getLowestPriorityOrder();

        if (order == null) {
            return ResponseEntity.badRequest().body("Priority tree is empty.");
        }

        return ResponseEntity.ok(order);
    }
}