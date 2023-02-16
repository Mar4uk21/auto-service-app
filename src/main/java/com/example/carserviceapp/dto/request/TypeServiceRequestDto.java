package com.example.carserviceapp.dto.request;

import com.example.carserviceapp.model.enums.PaymentStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeServiceRequestDto {
    private Long orderId;
    private Long mechanicId;
    private BigDecimal price;
    private PaymentStatus paymentStatus;
}
