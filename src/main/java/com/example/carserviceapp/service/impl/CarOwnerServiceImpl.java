package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.repository.CarOwnerRepository;
import com.example.carserviceapp.service.CarOwnerService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerRepository carOwnerRepository;

    public CarOwnerServiceImpl(CarOwnerRepository carOwnerRepository) {
        this.carOwnerRepository = carOwnerRepository;
    }

    @Override
    public CarOwner save(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    @Override
    public CarOwner update(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    @Override
    public List<Order> getOrdersList(CarOwner carOwner) {
        return carOwnerRepository.getById(carOwner.getId()).getOrders();
    }

    @Override
    public CarOwner get(Long id) {
        return carOwnerRepository.getById(id);
    }
}
