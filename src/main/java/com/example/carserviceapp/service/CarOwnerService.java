package com.example.carserviceapp.service;

import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.model.Order;
import java.util.List;

public interface CarOwnerService {
    CarOwner save(CarOwner carOwner);

    CarOwner update(CarOwner carOwner);

    List<Order> getOrdersList(CarOwner carOwner);

    CarOwner get(Long id);
}
