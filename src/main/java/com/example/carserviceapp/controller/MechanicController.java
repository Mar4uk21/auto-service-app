package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.mapper.RequestDtoMapper;
import com.example.carserviceapp.dto.mapper.ResponseDtoMapper;
import com.example.carserviceapp.dto.request.MechanicRequestDto;
import com.example.carserviceapp.dto.response.MechanicResponseDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.MechanicService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mechanics")
public class MechanicController {
    private final MechanicService mechanicService;
    private final RequestDtoMapper<MechanicRequestDto, Mechanic> mechanicRequestDtoMapper;
    private final ResponseDtoMapper<MechanicResponseDto, Mechanic> mechanicResponseDtoMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper;

    public MechanicController(MechanicService mechanicService,
                              RequestDtoMapper<MechanicRequestDto,
                                      Mechanic> mechanicRequestDtoMapper,
                              ResponseDtoMapper<MechanicResponseDto,
                                      Mechanic> mechanicResponseDtoMapper,
                              ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper) {
        this.mechanicService = mechanicService;
        this.mechanicRequestDtoMapper = mechanicRequestDtoMapper;
        this.mechanicResponseDtoMapper = mechanicResponseDtoMapper;
        this.orderResponseDtoMapper = orderResponseDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new mechanic")
    public MechanicResponseDto add(@RequestBody MechanicRequestDto mechanicRequestDto) {
        Mechanic mechanic = mechanicService
                .save(mechanicRequestDtoMapper.mapToModel(mechanicRequestDto));
        return mechanicResponseDtoMapper.mapToDto(mechanic);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update mechanic by id")
    public MechanicResponseDto update(@PathVariable Long id,
                                      @RequestBody MechanicRequestDto mechanicRequestDto) {
        Mechanic mechanic = mechanicRequestDtoMapper.mapToModel(mechanicRequestDto);
        mechanic.setId(id);
        mechanicService.update(mechanic);
        return mechanicResponseDtoMapper.mapToDto(mechanic);
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "Get completed orders by id")
    public List<OrderResponseDto> getCompletedOrders(@PathVariable Long id) {
        return mechanicService.getOrdersList(mechanicService.get(id))
                .stream()
                .map(orderResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    @ApiOperation(value = "Returns mechanic's salary by id")
    public BigDecimal calculationSalaryForTheMechanic(@PathVariable Long id) {
        BigDecimal salary = mechanicService.calculateSalary(id);
        return salary;
    }
}
