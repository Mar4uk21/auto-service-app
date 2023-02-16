package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.CarOwnerRequestDto;
import com.example.carserviceapp.dto.response.CarOwnerResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.CarService;
import com.example.carserviceapp.service.OrderService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CarOwnerMapper implements ResponseDtoMapper<CarOwnerResponseDto, CarOwner>,
        RequestDtoMapper<CarOwnerRequestDto, CarOwner> {
    private final CarService carService;
    private final OrderService orderService;

    public CarOwnerMapper(CarService carService, OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    @Override
    public CarOwner mapToModel(CarOwnerRequestDto dto) {
        CarOwner carOwner = new CarOwner();
        carOwner.setCars(carService.getAllCars(dto.getCarsId()));
        carOwner.setOrders(orderService.getAllOrders(dto.getOrdersId()));
        return carOwner;
    }

    @Override
    public CarOwnerResponseDto mapToDto(CarOwner carOwner) {
        CarOwnerResponseDto carOwnerResponseDto = new CarOwnerResponseDto();
        carOwnerResponseDto.setId(carOwner.getId());
        carOwnerResponseDto.setCarsId(carOwner.getCars()
                .stream()
                .map(Car::getId)
                .toList());
        carOwnerResponseDto.setOrdersId(carOwner.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return carOwnerResponseDto;
    }
}
