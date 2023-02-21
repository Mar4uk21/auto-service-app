package com.example.carserviceapp.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeServiceRequestDto {
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
}
