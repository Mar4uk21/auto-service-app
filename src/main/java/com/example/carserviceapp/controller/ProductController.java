package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.mapper.RequestDtoMapper;
import com.example.carserviceapp.dto.mapper.ResponseDtoMapper;
import com.example.carserviceapp.dto.request.ProductRequestDto;
import com.example.carserviceapp.dto.response.ProductResponseDto;
import com.example.carserviceapp.model.Product;
import com.example.carserviceapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;

    public ProductController(ProductService productService,
                             RequestDtoMapper<ProductRequestDto,
                                     Product> productRequestDtoMapper,
                             ResponseDtoMapper<ProductResponseDto,
                                     Product> productResponseDtoMapper) {
        this.productService = productService;
        this.productRequestDtoMapper = productRequestDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new product")
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService
                .save(productRequestDtoMapper.mapToModel(productRequestDto));
        return productResponseDtoMapper.mapToDto(product);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update product by id")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        productService.update(product);
        return productResponseDtoMapper.mapToDto(product);
    }
}
