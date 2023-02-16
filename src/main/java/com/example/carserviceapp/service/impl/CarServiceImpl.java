package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.repository.CarRepository;
import com.example.carserviceapp.service.CarService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car get(Long id) {
        return carRepository.getById(id);
    }

    @Override
    public List<Car> getAllCars(List<Long> carsIds) {
        return carRepository.findAllById(carsIds);
    }
}
