package com.chrisking.warehouse_inventory_system.service;

import com.chrisking.warehouse_inventory_system.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    @Test
    void sortByPriceShouldReturnProductsInAscendingPriceOrder() {
        ProductService productService = new ProductService();

        Product product1 = new Product("Laptop", 1200.00, 10);
        Product product2 = new Product("Mouse", 25.00, 50);
        Product product3 = new Product("Keyboard", 75.00, 30);
        Product product4 = new Product("Monitor", 300.00, 20);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        List<Product> sortedProducts = productService.sortByPrice(products);

        assertEquals(25.00, sortedProducts.get(0).getPrice());
        assertEquals(75.00, sortedProducts.get(1).getPrice());
        assertEquals(300.00, sortedProducts.get(2).getPrice());
        assertEquals(1200.00, sortedProducts.get(3).getPrice());
    }
}