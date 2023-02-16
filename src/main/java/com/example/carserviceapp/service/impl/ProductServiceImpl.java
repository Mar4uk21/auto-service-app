package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Product;
import com.example.carserviceapp.repository.ProductRepository;
import com.example.carserviceapp.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllProducts(List<Long> productsIds) {
        return productRepository.findAllById(productsIds);
    }
}
