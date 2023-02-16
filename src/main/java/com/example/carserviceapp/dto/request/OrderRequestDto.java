package com.example.carserviceapp.dto.request;

import com.example.carserviceapp.model.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private Long carId;
    private String description;
    private LocalDateTime dataOrder;
    private List<Long> servicesId;
    private List<Long> productsId;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private LocalDateTime timeToFinish;
}
