package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.*;
import com.example.carserviceapp.model.enums.OrderStatus;
import com.example.carserviceapp.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldReturnValidOrderStatusAccepted() {
        Mockito.when(orderRepository.getById(1L))
                .thenReturn(new Order(1L,new Car(),"change engine",
                        LocalDateTime.now(), Collections.emptyList(),Collections.emptyList(),
                        OrderStatus.DURING,BigDecimal.valueOf(1000),LocalDateTime.now()));
        Order actual = orderService.get(1L);
        orderService.changeOrderStatus(1L,1);
        Assertions.assertEquals(OrderStatus.ACCEPTED,actual.getOrderStatus());
    }

    @Test
    void shouldReturnValidOrderStatusDuring() {
        Mockito.when(orderRepository.getById(1L))
                .thenReturn(new Order(1L,new Car(),"change engine",
                        LocalDateTime.now(), Collections.emptyList(),Collections.emptyList(),
                        OrderStatus.ACCEPTED,BigDecimal.valueOf(1000),LocalDateTime.now()));
        Order actual = orderService.get(1L);
        orderService.changeOrderStatus(1L,2);
        Assertions.assertEquals(OrderStatus.DURING,actual.getOrderStatus());
    }

    @Test
    void shouldReturnValidOrderStatusUncompleted() {
        Mockito.when(orderRepository.getById(1L))
                .thenReturn(new Order(1L,new Car(),"change engine",
                        LocalDateTime.now(), Collections.emptyList(),Collections.emptyList(),
                        OrderStatus.ACCEPTED,BigDecimal.valueOf(1000),LocalDateTime.now()));
        Order actual = orderService.get(1L);
        orderService.changeOrderStatus(1L,3);
        Assertions.assertEquals(OrderStatus.UNCOMPLETED,actual.getOrderStatus());
    }

    @Test
    void shouldReturnValidOrderStatusCompleted() {
        Mockito.when(orderRepository.getById(1L))
                .thenReturn(new Order(1L,new Car(),"change engine",
                        LocalDateTime.now(), Collections.emptyList(),Collections.emptyList(),
                        OrderStatus.ACCEPTED,BigDecimal.valueOf(1000),LocalDateTime.now()));
        Order actual = orderService.get(1L);
        orderService.changeOrderStatus(1L,4);
        Assertions.assertEquals(OrderStatus.COMPLETED,actual.getOrderStatus());
    }

    @Test
    void shouldReturnValidTotalPriceOfOrder() {
        Mockito.when(orderRepository.getById(1L))
                .thenReturn(new Order(1L,new Car(33L, "Porsche",
                        "911", 2012L, "7777",
                        new CarOwner(1L, Collections.emptyList(), Collections.emptyList())),
                        "change engine", LocalDateTime.now(),
                        List.of(new TypeService(1L, BigDecimal.valueOf(1000)),
                        new TypeService(2L, BigDecimal.valueOf(2000))),List.of(new Product(1L, "Engine", BigDecimal.valueOf(10000)),
                        new Product(2L, "Filter", BigDecimal.valueOf(500))),
                        OrderStatus.ACCEPTED,BigDecimal.valueOf(0),LocalDateTime.now()));
        BigDecimal expected = BigDecimal.valueOf(13500.0);
        BigDecimal actual = orderService.totalPriceOfOrder(1L);
        Assertions.assertEquals(expected,actual);
    }
}
