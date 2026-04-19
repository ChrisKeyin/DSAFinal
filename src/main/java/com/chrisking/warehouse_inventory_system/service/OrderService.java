package com.chrisking.warehouse_inventory_system.service;

import com.chrisking.warehouse_inventory_system.bst.OrderBST;
import com.chrisking.warehouse_inventory_system.dto.OrderItemRequest;
import com.chrisking.warehouse_inventory_system.dto.OrderRequest;
import com.chrisking.warehouse_inventory_system.entity.Customer;
import com.chrisking.warehouse_inventory_system.entity.Order;
import com.chrisking.warehouse_inventory_system.entity.OrderItem;
import com.chrisking.warehouse_inventory_system.entity.Product;
import com.chrisking.warehouse_inventory_system.repository.CustomerRepository;
import com.chrisking.warehouse_inventory_system.repository.OrderRepository;
import com.chrisking.warehouse_inventory_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderBST orderBST;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(OrderRequest orderRequest) {
        if (orderRequest.getPriorityLevel() < 1 || orderRequest.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("Priority level must be between 1 and 10.");
        }

        if (orderRequest.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID is required.");
        }

        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found with id: " + orderRequest.getCustomerId()));

        Order order = new Order();
        order.setOrderDate(orderRequest.getOrderDate());
        order.setPriorityLevel(orderRequest.getPriorityLevel());
        order.setCustomer(customer);

        List<OrderItem> orderItems = new ArrayList<>();

        if (orderRequest.getItems() != null) {
            for (OrderItemRequest itemRequest : orderRequest.getItems()) {
                if (itemRequest.getProductId() == null) {
                    throw new IllegalArgumentException("Each order item must have a product ID.");
                }

                if (itemRequest.getQuantity() <= 0) {
                    throw new IllegalArgumentException("Each order item must have a quantity greater than 0.");
                }

                Product product = productRepository.findById(itemRequest.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Product not found with id: " + itemRequest.getProductId()));

                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setQuantity(itemRequest.getQuantity());
                orderItem.setOrder(order);

                orderItems.add(orderItem);
            }
        }

        order.setOrderItems(orderItems);

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