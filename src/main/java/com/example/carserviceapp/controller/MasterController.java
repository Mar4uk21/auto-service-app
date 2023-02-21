package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.mapper.RequestDtoMapper;
import com.example.carserviceapp.dto.mapper.ResponseDtoMapper;
import com.example.carserviceapp.dto.request.MasterRequestDto;
import com.example.carserviceapp.dto.response.MasterResponseDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.MasterService;
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
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final RequestDtoMapper<MasterRequestDto, Master> masterRequestDtoMapper;
    private final ResponseDtoMapper<MasterResponseDto, Master> masterResponseDtoMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper;

    public MasterController(MasterService masterService,
                            RequestDtoMapper<MasterRequestDto,
                                      Master> masterRequestDtoMapper,
                            ResponseDtoMapper<MasterResponseDto,
                                      Master> masterResponseDtoMapper,
                            ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper) {
        this.masterService = masterService;
        this.masterRequestDtoMapper = masterRequestDtoMapper;
        this.masterResponseDtoMapper = masterResponseDtoMapper;
        this.orderResponseDtoMapper = orderResponseDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new master")
    public MasterResponseDto add(@RequestBody MasterRequestDto masterRequestDto) {
        Master master = masterService
                .save(masterRequestDtoMapper.mapToModel(masterRequestDto));
        return masterResponseDtoMapper.mapToDto(master);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update master by id")
    public MasterResponseDto update(@PathVariable Long id,
                                    @RequestBody MasterRequestDto masterRequestDto) {
        Master master = masterRequestDtoMapper.mapToModel(masterRequestDto);
        master.setId(id);
        masterService.update(master);
        return masterResponseDtoMapper.mapToDto(master);
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "Get completed orders by id")
    public List<OrderResponseDto> getCompletedOrders(@PathVariable Long id) {
        return masterService.getOrdersList(masterService.get(id))
                .stream()
                .map(orderResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    @ApiOperation(value = "Returns master's salary by id")
    public BigDecimal calculationSalaryForTheMaster(@PathVariable Long id) {
        BigDecimal salary = masterService.calculateSalary(id);
        return salary;
    }
}
