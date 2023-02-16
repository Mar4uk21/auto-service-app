package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.mapper.RequestDtoMapper;
import com.example.carserviceapp.dto.mapper.ResponseDtoMapper;
import com.example.carserviceapp.dto.request.CarRequestDto;
import com.example.carserviceapp.dto.response.CarResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.service.CarOwnerService;
import com.example.carserviceapp.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarOwnerService carOwnerService;
    private final RequestDtoMapper<CarRequestDto, Car> carRequestDtoMapper;
    private final ResponseDtoMapper<CarResponseDto, Car> carResponseDtoMapper;

    public CarController(CarService carService,
                         CarOwnerService carOwnerService,
                         RequestDtoMapper<CarRequestDto, Car> carRequestDtoMapper,
                         ResponseDtoMapper<CarResponseDto, Car> carResponseDtoMapper) {
        this.carService = carService;
        this.carOwnerService = carOwnerService;
        this.carRequestDtoMapper = carRequestDtoMapper;
        this.carResponseDtoMapper = carResponseDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new car")
    public CarResponseDto add(@RequestBody CarRequestDto carRequestDto) {
        Car car = carService
                .save(carRequestDtoMapper.mapToModel(carRequestDto));
        CarOwner owner = car.getOwner();
        owner.getCars().add(car);
        carOwnerService.update(owner);
        return carResponseDtoMapper.mapToDto(car);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update car by id")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto carRequestDto) {
        Car car = carRequestDtoMapper.mapToModel(carRequestDto);
        car.setId(id);
        carService.update(car);
        return carResponseDtoMapper.mapToDto(car);
    }
}
