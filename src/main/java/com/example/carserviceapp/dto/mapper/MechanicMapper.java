package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.MechanicRequestDto;
import com.example.carserviceapp.dto.response.MechanicResponseDto;
import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.OrderService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MechanicMapper implements RequestDtoMapper<MechanicRequestDto, Mechanic>,
        ResponseDtoMapper<MechanicResponseDto, Mechanic> {
    private final OrderService orderService;

    public MechanicMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Mechanic mapToModel(MechanicRequestDto dto) {
        Mechanic mechanic = new Mechanic();
        mechanic.setFullName(dto.getFullName());
        mechanic.setCompletedOrders(orderService.getAllOrders(dto.getCompletedOrdersId()));
        return mechanic;
    }

    @Override
    public MechanicResponseDto mapToDto(Mechanic mechanic) {
        MechanicResponseDto mechanicResponseDto = new MechanicResponseDto();
        mechanicResponseDto.setId(mechanic.getId());
        mechanicResponseDto.setFullName(mechanic.getFullName());
        mechanicResponseDto.setCompletedOrdersId(mechanic.getCompletedOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return mechanicResponseDto;
    }
}
