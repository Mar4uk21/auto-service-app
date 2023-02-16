package com.example.carserviceapp.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarOwnerResponseDto {
    private Long id;
    private List<Long> carsId;
    private List<Long> ordersId;
}
