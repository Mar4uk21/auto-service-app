package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.MasterRequestDto;
import com.example.carserviceapp.dto.response.MasterResponseDto;
import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.OrderService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper implements RequestDtoMapper<MasterRequestDto, Master>,
        ResponseDtoMapper<MasterResponseDto, Master> {
    private final OrderService orderService;

    public MasterMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Master mapToModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setFullName(dto.getFullName());
        master.setCompletedOrders(orderService.getAllOrders(dto.getCompletedOrdersId()));
        return master;
    }

    @Override
    public MasterResponseDto mapToDto(Master master) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(master.getId());
        masterResponseDto.setFullName(master.getFullName());
        masterResponseDto.setCompletedOrdersId(master.getCompletedOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return masterResponseDto;
    }
}
