package com.chrisking.warehouse_inventory_system.bst;

import com.chrisking.warehouse_inventory_system.entity.Order;

public class OrderNode {

    Order data;
    OrderNode left;
    OrderNode right;

    public OrderNode(Order data) {
        this.data = data;
    }
}