package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product update(Product product);

    Product get(Long id);

    List<Product> getAllProducts(List<Long> productsIds);
}
