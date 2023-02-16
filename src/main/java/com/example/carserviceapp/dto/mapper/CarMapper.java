package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.CarRequestDto;
import com.example.carserviceapp.dto.response.CarResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.service.CarOwnerService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements ResponseDtoMapper<CarResponseDto, Car>,
        RequestDtoMapper<CarRequestDto, Car> {
    private final CarOwnerService carOwnerService;

    public CarMapper(CarOwnerService carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setNumberOfCar(dto.getNumberOfCar());
        car.setOwner(carOwnerService.get(dto.getOwnerId()));
        car.setManufacturer(dto.getManufacturer());
        car.setYearOfRelease(dto.getYearOfRelease());
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(car.getId());
        carResponseDto.setNumberOfCar(car.getNumberOfCar());
        carResponseDto.setManufacturer(car.getManufacturer());
        carResponseDto.setOwnerId(car.getOwner().getId());
        carResponseDto.setModel(car.getModel());
        carResponseDto.setYearOfRelease(car.getYearOfRelease());
        return carResponseDto;
    }
}
