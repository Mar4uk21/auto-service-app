package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.Product;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    Order get(Long id);

    List<Product> getProductsFromOrder(Long id);

    void changeOrderStatus(Long id, int status);

    BigDecimal totalPriceOfOrder(Long id);

    List<Order> getAllOrders(List<Long> orderIds);
}
