package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.mapper.RequestDtoMapper;
import com.example.carserviceapp.dto.mapper.ResponseDtoMapper;
import com.example.carserviceapp.dto.request.TypeServiceRequestDto;
import com.example.carserviceapp.dto.response.TypeServiceResponseDto;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.service.OrderService;
import com.example.carserviceapp.service.TypeServiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type-services")
public class TypeServiceController {
    private final TypeServiceService typeServiceService;
    private final OrderService orderService;
    private final ResponseDtoMapper<TypeServiceResponseDto,
            TypeService> typeServiceResponseDtoMapper;
    private final RequestDtoMapper<TypeServiceRequestDto,
            TypeService> typeServiceRequestDtoMapper;

    public TypeServiceController(TypeServiceService typeServiceService,
                                 OrderService orderService,
                                 ResponseDtoMapper<TypeServiceResponseDto,
                                         TypeService> typeServiceResponseDtoMapper,
                                 RequestDtoMapper<TypeServiceRequestDto,
                                         TypeService> typeServiceRequestDtoMapper) {
        this.typeServiceService = typeServiceService;
        this.orderService = orderService;
        this.typeServiceResponseDtoMapper = typeServiceResponseDtoMapper;
        this.typeServiceRequestDtoMapper = typeServiceRequestDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new type service")
    public TypeServiceResponseDto add(@RequestBody TypeServiceRequestDto typeServiceRequestDto) {
        TypeService typeService = typeServiceService
                .save(typeServiceRequestDtoMapper.mapToModel(typeServiceRequestDto));
        Order order = typeService.getOrder();
        order.getServices().add(typeService);
        orderService.update(order);
        return typeServiceResponseDtoMapper.mapToDto(typeService);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update type service by id")
    public TypeServiceResponseDto update(@PathVariable Long id,
                                         @RequestBody TypeServiceRequestDto typeServiceRequestDto) {
        TypeService typeService = typeServiceRequestDtoMapper.mapToModel(typeServiceRequestDto);
        typeService.setId(id);
        typeServiceService.update(typeService);
        return typeServiceResponseDtoMapper.mapToDto(typeService);
    }

    @PutMapping("/{id}/change")
    @ApiOperation(value = "Change payment status by id")
    public TypeServiceResponseDto changePaymentStatus(@PathVariable Long id) {
        TypeService typeService = typeServiceService.get(id);
        typeService.setId(id);
        typeServiceService.changePaymentStatus(id);
        return typeServiceResponseDtoMapper.mapToDto(typeServiceService.update(typeService));
    }
}
