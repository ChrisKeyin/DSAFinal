package com.chrisking.warehouse_inventory_system.bst;

import com.chrisking.warehouse_inventory_system.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderBSTTest {

    private OrderBST orderBST;

    @BeforeEach
    void setUp() {
        orderBST = new OrderBST();
    }

    private Order createOrder(int priorityLevel) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setPriorityLevel(priorityLevel);
        return order;
    }

    @Test
    void inorderTraversalShouldReturnOrdersInSortedPriorityOrder() {
        orderBST.insert(createOrder(5));
        orderBST.insert(createOrder(2));
        orderBST.insert(createOrder(8));
        orderBST.insert(createOrder(1));
        orderBST.insert(createOrder(3));

        List<Order> result = orderBST.inorderTraversal();

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getPriorityLevel());
        assertEquals(2, result.get(1).getPriorityLevel());
        assertEquals(3, result.get(2).getPriorityLevel());
        assertEquals(5, result.get(3).getPriorityLevel());
        assertEquals(8, result.get(4).getPriorityLevel());
    }

    @Test
    void findHighestShouldReturnOrderWithHighestPriority() {
        orderBST.insert(createOrder(4));
        orderBST.insert(createOrder(9));
        orderBST.insert(createOrder(2));
        orderBST.insert(createOrder(7));

        Order highest = orderBST.findHighest();

        assertNotNull(highest);
        assertEquals(9, highest.getPriorityLevel());
    }
}