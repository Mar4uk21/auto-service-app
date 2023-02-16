package com.example.carserviceapp.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarOwnerRequestDto {
    private List<Long> carsId;
    private List<Long> ordersId;
}
