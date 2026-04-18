package com.chrisking.warehouse_inventory_system.repository;

import com.chrisking.warehouse_inventory_system.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}