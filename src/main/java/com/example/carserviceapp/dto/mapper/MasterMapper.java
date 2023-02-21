package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.MasterRequestDto;
import com.example.carserviceapp.dto.response.MasterResponseDto;
import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Order;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper implements RequestDtoMapper<MasterRequestDto, Master>,
        ResponseDtoMapper<MasterResponseDto, Master> {

    @Override
    public Master mapToModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setFullName(dto.getFullName());
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
