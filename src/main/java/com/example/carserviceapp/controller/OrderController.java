package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.mapper.RequestDtoMapper;
import com.example.carserviceapp.dto.mapper.ResponseDtoMapper;
import com.example.carserviceapp.dto.request.OrderRequestDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.CarOwnerService;
import com.example.carserviceapp.service.OrderService;
import com.example.carserviceapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CarOwnerService ownerService;
    private final ProductService productService;
    private final RequestDtoMapper<OrderRequestDto, Order> orderRequestDtoMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper;

    public OrderController(OrderService orderService,
                           CarOwnerService ownerService,
                           RequestDtoMapper<OrderRequestDto, Order> orderRequestDtoMapper,
                           ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper,
                           ProductService productService) {
        this.orderService = orderService;
        this.ownerService = ownerService;
        this.orderRequestDtoMapper = orderRequestDtoMapper;
        this.orderResponseDtoMapper = orderResponseDtoMapper;
        this.productService = productService;
    }

    @PostMapping
    @ApiOperation(value = "Create new order")
    public OrderResponseDto add(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderService.save(orderRequestDtoMapper.mapToModel(orderRequestDto));
        CarOwner owner = order.getCar().getOwner();
        owner.getOrders().add(order);
        ownerService.update(owner);
        return orderResponseDtoMapper.mapToDto(order);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update order by id")
    public OrderResponseDto update(@PathVariable Long id,
                                   @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderRequestDtoMapper.mapToModel(orderRequestDto);
        order.setId(id);
        orderService.update(order);
        return orderResponseDtoMapper.mapToDto(order);
    }

    @PostMapping("/{orderId}/{productId}")
    @ApiOperation(value = "Add products to order")
    public OrderResponseDto addProductsToOrder(@PathVariable Long productId,
                                               @PathVariable Long orderId) {
        Order order = orderService.get(orderId);
        order.getProducts().add(productService.get(productId));
        orderService.update(order);
        return orderResponseDtoMapper.mapToDto(order);
    }

    @PutMapping("/{id}/{statusCode}")
    @ApiOperation(value = "Change order status by id")
    public OrderResponseDto changeOrderStatus(@PathVariable Long id,
                                              @PathVariable int statusCode) {
        Order order = orderService.get(id);
        orderService.changeOrderStatus(id,statusCode);
        return orderResponseDtoMapper.mapToDto(orderService.update(order));
    }

    @GetMapping("/{id}/totalPrice")
    @ApiOperation(value = "Get total price by order id")
    public BigDecimal getTotalPrice(@PathVariable Long id) {
        Order order = orderService.get(id);
        order.setTotalPrice(orderService.totalPriceOfOrder(order.getId()));
        orderService.update(order);
        return orderService.get(id).getTotalPrice();
    }
}
