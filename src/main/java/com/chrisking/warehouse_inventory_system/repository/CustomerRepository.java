package com.chrisking.warehouse_inventory_system.repository;

import com.chrisking.warehouse_inventory_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}