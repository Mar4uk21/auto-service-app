package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.OrderRequestDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.model.*;
import com.example.carserviceapp.model.enums.OrderStatus;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.service.impl.CarServiceImpl;
import com.example.carserviceapp.service.impl.ProductServiceImpl;
import com.example.carserviceapp.service.impl.TypeServiceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {
    private static final OrderRequestDto TEST_ORDER_REQUEST_DTO = new OrderRequestDto(1L,"Change engine",
            LocalDateTime.now(), List.of(1L,2L),List.of(1L,2L), OrderStatus.ACCEPTED, BigDecimal.valueOf(5000),LocalDateTime.now());
    private static final List<Product> TEST_PRODUCTS = List.of(new Product(1L,"Engine",BigDecimal.valueOf(10000)));
    private static final Car TEST_CAR = new Car(1L,"BMW","E40",2022L,"777",new CarOwner());
    private static final List<TypeService> TEST_TYPE_SERVICES = List.of(new TypeService(1L,new Order(),
            new Master(),BigDecimal.valueOf(1000), PaymentStatus.UNPAID));
    private static final Order TEST_ORDER = new Order(1L,TEST_CAR,"Change filter",
            LocalDateTime.now(),TEST_TYPE_SERVICES,TEST_PRODUCTS,OrderStatus.ACCEPTED,BigDecimal.valueOf(7000),LocalDateTime.now());

    @InjectMocks
    private OrderMapper orderMapper;

    @Mock
    private CarServiceImpl carService;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private TypeServiceServiceImpl typeServiceService;

    @Test
    void correctMappingOrderRequestDtoToOrder() {
        Mockito.when(carService.get(TEST_ORDER_REQUEST_DTO.getCarId())).thenReturn(TEST_CAR);
        Mockito.when(productService.getAllProducts(TEST_ORDER_REQUEST_DTO.getProductsId()))
                .thenReturn(TEST_PRODUCTS);
        Mockito.when(typeServiceService.getAllTypeServices(TEST_ORDER_REQUEST_DTO.getServicesId()))
                .thenReturn(TEST_TYPE_SERVICES);
        Order expected = new Order(null,carService.get(TEST_ORDER_REQUEST_DTO.getCarId()),
                TEST_ORDER_REQUEST_DTO.getDescription(),TEST_ORDER_REQUEST_DTO.getDataOrder(),
                typeServiceService.getAllTypeServices(TEST_ORDER_REQUEST_DTO.getServicesId()),
                productService.getAllProducts(TEST_ORDER_REQUEST_DTO.getProductsId()),
                TEST_ORDER_REQUEST_DTO.getOrderStatus(),TEST_ORDER_REQUEST_DTO.getTotalPrice(),
                TEST_ORDER_REQUEST_DTO.getTimeToFinish());
        Order actual = orderMapper.mapToModel(TEST_ORDER_REQUEST_DTO);
        Assertions.assertEquals(actual.getCar(), expected.getCar());
        Assertions.assertEquals(actual.getDescription(), expected.getDescription());
        Assertions.assertEquals(actual.getServices(), expected.getServices());
        Assertions.assertEquals(actual.getProducts(), expected.getProducts());
        Assertions.assertEquals(actual.getOrderStatus(), expected.getOrderStatus());
        Assertions.assertEquals(actual.getTotalPrice(), expected.getTotalPrice());
        Assertions.assertEquals(actual.getTimeToFinish(), expected.getTimeToFinish());
    }

    @Test
    void correctMappingOrderToOrderResponseDto() {
        OrderResponseDto expected = new OrderResponseDto(TEST_ORDER.getId(),TEST_ORDER.getCar().getId(),TEST_ORDER.getDescription(),TEST_ORDER.getDataOrder(),
                TEST_ORDER.getServices().stream().map(TypeService::getId).collect(Collectors.toList()),
                TEST_ORDER.getProducts().stream().map(Product::getId).collect(Collectors.toList()),
                TEST_ORDER.getOrderStatus(),TEST_ORDER.getTotalPrice(),TEST_ORDER.getTimeToFinish());
        OrderResponseDto actual = orderMapper.mapToDto(TEST_ORDER);
        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getCarId(), expected.getCarId());
        Assertions.assertEquals(actual.getDescription(), expected.getDescription());
        Assertions.assertEquals(actual.getServicesId(), expected.getServicesId());
        Assertions.assertEquals(actual.getProductsId(), expected.getProductsId());
        Assertions.assertEquals(actual.getOrderStatus(), expected.getOrderStatus());
        Assertions.assertEquals(actual.getTotalPrice(), expected.getTotalPrice());
        Assertions.assertEquals(actual.getTimeToFinish(), expected.getTimeToFinish());
    }
}
