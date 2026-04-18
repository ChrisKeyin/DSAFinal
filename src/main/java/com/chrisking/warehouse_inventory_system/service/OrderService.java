package com.chrisking.warehouse_inventory_system.service;

import com.chrisking.warehouse_inventory_system.bst.OrderBST;
import com.chrisking.warehouse_inventory_system.dto.OrderRequest;
import com.chrisking.warehouse_inventory_system.entity.Customer;
import com.chrisking.warehouse_inventory_system.entity.Order;
import com.chrisking.warehouse_inventory_system.repository.CustomerRepository;
import com.chrisking.warehouse_inventory_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderBST orderBST;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(OrderRequest orderRequest) {
        if (orderRequest.getPriorityLevel() < 1 || orderRequest.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("Priority level must be between 1 and 10.");
        }

        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + orderRequest.getCustomerId()));

        Order order = new Order();
        order.setOrderDate(orderRequest.getOrderDate());
        order.setPriorityLevel(orderRequest.getPriorityLevel());
        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    public String addOrdersToPriorityTree() {
        List<Order> orders = orderRepository.findAll();

        orderBST.clear();

        for (Order order : orders) {
            orderBST.insert(order);
        }

        return "Orders added to priority tree successfully.";
    }

    public List<Order> getOrdersInPriorityOrder() {
        return orderBST.inorderTraversal();
    }

    public Order getHighestPriorityOrder() {
        return orderBST.findHighest();
    }

    public Order getLowestPriorityOrder() {
        return orderBST.findLowest();
    }
}