package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface MechanicService {
    Mechanic save(Mechanic mechanic);

    Mechanic update(Mechanic mechanic);

    Mechanic get(Long id);

    List<Order> getOrdersList(Mechanic mechanic);

    BigDecimal calculateSalary(Long id);
}
