package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.mapper.RequestDtoMapper;
import com.example.carserviceapp.dto.mapper.ResponseDtoMapper;
import com.example.carserviceapp.dto.request.CarOwnerRequestDto;
import com.example.carserviceapp.dto.response.CarOwnerResponseDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.CarOwnerService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final RequestDtoMapper<CarOwnerRequestDto, CarOwner> carOwnerRequestDtoMapper;
    private final ResponseDtoMapper<CarOwnerResponseDto, CarOwner> carOwnerResponseDtoMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper;

    public CarOwnerController(CarOwnerService carOwnerService,
                              RequestDtoMapper<CarOwnerRequestDto,
                                      CarOwner> carOwnerRequestDtoMapper,
                              ResponseDtoMapper<CarOwnerResponseDto,
                                      CarOwner> carOwnerResponseDtoMapper,
                              ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper) {
        this.carOwnerService = carOwnerService;
        this.carOwnerRequestDtoMapper = carOwnerRequestDtoMapper;
        this.carOwnerResponseDtoMapper = carOwnerResponseDtoMapper;
        this.orderResponseDtoMapper = orderResponseDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new car owner")
    public CarOwnerResponseDto add(@RequestBody CarOwnerRequestDto carOwnerRequestDto) {
        CarOwner carOwner = carOwnerService
                .save(carOwnerRequestDtoMapper.mapToModel(carOwnerRequestDto));
        return carOwnerResponseDtoMapper.mapToDto(carOwner);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update car owner by id")
    public CarOwnerResponseDto update(@PathVariable Long id,
                                      @RequestBody CarOwnerRequestDto carOwnerRequestDto) {
        CarOwner carOwner = carOwnerRequestDtoMapper.mapToModel(carOwnerRequestDto);
        carOwner.setId(id);
        carOwnerService.update(carOwner);
        return carOwnerResponseDtoMapper.mapToDto(carOwner);
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "Get orders list by id")
    public List<OrderResponseDto> getOrdersList(@PathVariable Long id) {
        return carOwnerService.getOrdersList(carOwnerService.get(id))
                .stream()
                .map(orderResponseDtoMapper::mapToDto)
                .toList();
    }
}
