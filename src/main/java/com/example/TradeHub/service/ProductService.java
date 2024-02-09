package com.example.TradeHub.service;

import com.example.TradeHub.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
     List<Product> productList = new ArrayList<>();
    private long ID = 0;

    {
        productList.add(new Product(++ID,
                "iPhone11",
                "Smartphone with great camera",
                500,
                "New York",
                "John Doe"));
        productList.add(new Product(++ID,
                "iPhone12",
                "camera better than 11",
                800,
                "Los Angeles",
                "Larisa Ivanova"));
    }


    public List<Product> listProducts() { return productList; }

    public void saveProduct(Product product) {
        product.setId(++ID);
        productList.add(product);
    }

    public void deleteProduct(Long id) {
        productList.removeIf(product -> product.getId().equals(id));
    }

    public Product getProductById(Long id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) return product;
        }
        return null;
    }

}
