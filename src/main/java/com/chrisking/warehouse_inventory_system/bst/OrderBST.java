package com.chrisking.warehouse_inventory_system.bst;

import com.chrisking.warehouse_inventory_system.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderBST {

    private OrderNode root;

    public void insert(Order order) {
        root = insertRecursive(root, order);
    }

    private OrderNode insertRecursive(OrderNode current, Order order) {
        if (current == null) {
            return new OrderNode(order);
        }

        if (order.getPriorityLevel() < current.data.getPriorityLevel()) {
            current.left = insertRecursive(current.left, order);
        } else {
            current.right = insertRecursive(current.right, order);
        }

        return current;
    }

    public List<Order> inorderTraversal() {
        List<Order> orders = new ArrayList<>();
        inorderRecursive(root, orders);
        return orders;
    }

    private void inorderRecursive(OrderNode node, List<Order> orders) {
        if (node == null) {
            return;
        }

        inorderRecursive(node.left, orders);
        orders.add(node.data);
        inorderRecursive(node.right, orders);
    }

    public Order findHighest() {
        if (root == null) {
            return null;
        }

        OrderNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    public Order findLowest() {
        if (root == null) {
            return null;
        }

        OrderNode current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }
}