package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.Product;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.model.enums.OrderStatus;
import com.example.carserviceapp.repository.OrderRepository;
import com.example.carserviceapp.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final double PRODUCT_DISCOUNT_PERCENT = 0.01;
    private static final double SERVICE_DISCOUNT_PERCENT = 0.02;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order get(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<Product> getProductsFromOrder(Long id) {
        return orderRepository.getById(id).getProducts();
    }

    @Override
    public void changeOrderStatus(Long id, int status) {
        Order order = orderRepository.getById(id);
        if (status == 1) {
            order.setOrderStatus(OrderStatus.ACCEPTED);
        } else if (status == 2) {
            order.setOrderStatus(OrderStatus.DURING);
        } else if (status == 3) {
            order.setOrderStatus(OrderStatus.UNCOMPLETED);
            order.setTimeToFinish(LocalDateTime.now());
        } else if (status == 4) {
            order.getServices().stream()
                    .map(TypeService::getMaster)
                    .map(Master::getCompletedOrders)
                    .map(orders -> orders.add(order))
                    .collect(Collectors.toList());
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setTimeToFinish(LocalDateTime.now());
        }
    }

    @Override
    public BigDecimal totalPriceOfOrder(Long id) {
        Order order = orderRepository.getById(id);
        BigDecimal sumProducts = order.getProducts().stream().map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal productDiscount = sumProducts
                .multiply(BigDecimal.valueOf(
                        order.getCar().getOwner().getOrders().size() * PRODUCT_DISCOUNT_PERCENT));
        BigDecimal resultSumProducts = sumProducts.subtract(productDiscount);

        BigDecimal sumServices = order.getServices().stream().map(TypeService::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal servicesDiscount = sumServices
                .multiply(BigDecimal.valueOf(
                        order.getCar().getOwner().getOrders().size() * SERVICE_DISCOUNT_PERCENT));
        BigDecimal resultSumServices = sumServices.subtract(servicesDiscount);

        BigDecimal result = resultSumProducts.add(resultSumServices);
        return result;
    }

    @Override
    public List<Order> getAllOrders(List<Long> orderIds) {
        return orderRepository.findAllById(orderIds);
    }
}
