package com.chrisking.warehouse_inventory_system.service;

import com.chrisking.warehouse_inventory_system.entity.Product;
import com.chrisking.warehouse_inventory_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getSortedProducts(String sortBy) {
        List<Product> products = new ArrayList<>(productRepository.findAll());

        if (sortBy == null) {
            throw new IllegalArgumentException("Sort type cannot be null. Use 'price' or 'stock'.");
        }

        if (sortBy.equalsIgnoreCase("price")) {
            return sortByPrice(products);
        } else if (sortBy.equalsIgnoreCase("stock")) {
            return sortByStock(products);
        } else {
            throw new IllegalArgumentException("Invalid sort type. Use 'price' or 'stock'.");
        }
    }

    public List<Product> sortByPrice(List<Product> products) {
        for (int i = 1; i < products.size(); i++) {
            Product current = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getPrice() > current.getPrice()) {
                products.set(j + 1, products.get(j));
                j--;
            }

            products.set(j + 1, current);
        }

        return products;
    }

    public List<Product> sortByStock(List<Product> products) {
        for (int i = 1; i < products.size(); i++) {
            Product current = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getStock() > current.getStock()) {
                products.set(j + 1, products.get(j));
                j--;
            }

            products.set(j + 1, current);
        }

        return products;
    }
}