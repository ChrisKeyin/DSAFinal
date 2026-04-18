package com.chrisking.warehouse_inventory_system.controller;

import com.chrisking.warehouse_inventory_system.entity.Product;
import com.chrisking.warehouse_inventory_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/sorted")
    public ResponseEntity<?> getSortedProducts(@RequestParam String by) {
        try {
            return ResponseEntity.ok(productService.getSortedProducts(by));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}