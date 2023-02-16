package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Car;
import java.util.List;

public interface CarService {
    Car save(Car car);

    Car update(Car car);

    Car get(Long id);

    List<Car> getAllCars(List<Long> carsIds);
}
