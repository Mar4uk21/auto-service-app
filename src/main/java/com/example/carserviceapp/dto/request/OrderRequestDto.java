package com.example.carserviceapp.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private Long carId;
    private String description;
    private List<Long> servicesId;
    private List<Long> productsId;
}
