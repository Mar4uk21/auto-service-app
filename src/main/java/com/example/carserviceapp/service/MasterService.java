package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface MasterService {
    Master save(Master master);

    Master update(Master master);

    Master get(Long id);

    List<Order> getOrdersList(Master master);

    BigDecimal calculateSalary(Long id);
}
