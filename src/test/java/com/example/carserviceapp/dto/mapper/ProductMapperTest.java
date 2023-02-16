package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.ProductRequestDto;
import com.example.carserviceapp.dto.response.ProductResponseDto;
import com.example.carserviceapp.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {
    private static final ProductRequestDto TEST_PRODUCT_REQUEST_DTO = new ProductRequestDto("Engine", BigDecimal.valueOf(10000));
    private static final Product TEST_PRODUCT = new Product(2L,"Filter",BigDecimal.valueOf(300));

    @InjectMocks
    private ProductMapper productMapper;

    @Test
    void correctMappingProductRequestDtoToProduct() {
        Product expected = new Product(null,TEST_PRODUCT_REQUEST_DTO.getTitle(),
                TEST_PRODUCT_REQUEST_DTO.getPrice());
        Product actual = productMapper.mapToModel(TEST_PRODUCT_REQUEST_DTO);
        Assertions.assertEquals(actual.getTitle(), expected.getTitle());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
    }

    @Test
    void correctMappingProductToProductResponseDto() {
        ProductResponseDto expected = new ProductResponseDto(TEST_PRODUCT.getId(),TEST_PRODUCT.getTitle(),
                TEST_PRODUCT.getPrice());
        ProductResponseDto actual = productMapper.mapToDto(TEST_PRODUCT);
        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getTitle(), expected.getTitle());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
    }
}
