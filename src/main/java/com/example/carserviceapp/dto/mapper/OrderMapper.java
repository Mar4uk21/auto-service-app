package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.OrderRequestDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.Product;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.service.CarService;
import com.example.carserviceapp.service.ProductService;
import com.example.carserviceapp.service.TypeServiceService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements ResponseDtoMapper<OrderResponseDto, Order>,
        RequestDtoMapper<OrderRequestDto, Order> {
    private final CarService carService;
    private final ProductService productService;
    private final TypeServiceService typeServiceService;

    public OrderMapper(CarService carService, ProductService productService,
                       TypeServiceService typeServiceService) {
        this.carService = carService;
        this.productService = productService;
        this.typeServiceService = typeServiceService;
    }

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.get(dto.getCarId()));
        order.setDataOrder(dto.getDataOrder());
        order.setOrderStatus(dto.getOrderStatus());
        order.setDescription(dto.getDescription());
        order.setProducts(productService.getAllProducts(dto.getProductsId()));
        order.setServices(typeServiceService.getAllTypeServices(dto.getServicesId()));
        order.setTotalPrice(dto.getTotalPrice());
        order.setTimeToFinish(dto.getTimeToFinish());
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setDataOrder(order.getDataOrder());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setProductsId(order.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setCarId(order.getCar().getId());
        orderResponseDto.setDescription(order.getDescription());
        orderResponseDto.setServicesId(order.getServices()
                .stream()
                .map(TypeService::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        orderResponseDto.setTimeToFinish(order.getTimeToFinish());
        return orderResponseDto;
    }
}
