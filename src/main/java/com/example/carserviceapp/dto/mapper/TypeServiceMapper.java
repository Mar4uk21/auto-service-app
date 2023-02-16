package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.TypeServiceRequestDto;
import com.example.carserviceapp.dto.response.TypeServiceResponseDto;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.service.MechanicService;
import com.example.carserviceapp.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class TypeServiceMapper implements RequestDtoMapper<TypeServiceRequestDto, TypeService>,
        ResponseDtoMapper<TypeServiceResponseDto, TypeService> {
    private final MechanicService mechanicService;
    private final OrderService orderService;

    public TypeServiceMapper(MechanicService mechanicService, OrderService orderService) {
        this.mechanicService = mechanicService;
        this.orderService = orderService;
    }

    @Override
    public TypeService mapToModel(TypeServiceRequestDto dto) {
        TypeService typeService = new TypeService();
        typeService.setOrder(orderService.get(dto.getOrderId()));
        typeService.setMechanic(mechanicService.get(dto.getMechanicId()));
        typeService.setPrice(dto.getPrice());
        typeService.setPaymentStatus(dto.getPaymentStatus());
        return typeService;
    }

    @Override
    public TypeServiceResponseDto mapToDto(TypeService typeService) {
        TypeServiceResponseDto typeServiceResponseDto = new TypeServiceResponseDto();
        typeServiceResponseDto.setId(typeService.getId());
        typeServiceResponseDto.setOrderId(typeService.getOrder().getId());
        typeServiceResponseDto.setMechanicId(typeService.getMechanic().getId());
        typeServiceResponseDto.setPrice(typeService.getPrice());
        typeServiceResponseDto.setPaymentStatus(typeService.getPaymentStatus());
        return typeServiceResponseDto;
    }
}
