package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.TypeServiceRequestDto;
import com.example.carserviceapp.dto.response.TypeServiceResponseDto;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.service.MasterService;
import com.example.carserviceapp.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class TypeServiceMapper implements RequestDtoMapper<TypeServiceRequestDto, TypeService>,
        ResponseDtoMapper<TypeServiceResponseDto, TypeService> {
    private final MasterService masterService;
    private final OrderService orderService;

    public TypeServiceMapper(MasterService masterService, OrderService orderService) {
        this.masterService = masterService;
        this.orderService = orderService;
    }

    @Override
    public TypeService mapToModel(TypeServiceRequestDto dto) {
        TypeService typeService = new TypeService();
        typeService.setOrder(orderService.get(dto.getOrderId()));
        typeService.setMaster(masterService.get(dto.getMasterId()));
        typeService.setPrice(dto.getPrice());
        typeService.setPaymentStatus(PaymentStatus.UNPAID);
        return typeService;
    }

    @Override
    public TypeServiceResponseDto mapToDto(TypeService typeService) {
        TypeServiceResponseDto typeServiceResponseDto = new TypeServiceResponseDto();
        typeServiceResponseDto.setId(typeService.getId());
        typeServiceResponseDto.setOrderId(typeService.getOrder().getId());
        typeServiceResponseDto.setMasterId(typeService.getMaster().getId());
        typeServiceResponseDto.setPrice(typeService.getPrice());
        typeServiceResponseDto.setPaymentStatus(typeService.getPaymentStatus());
        return typeServiceResponseDto;
    }
}
