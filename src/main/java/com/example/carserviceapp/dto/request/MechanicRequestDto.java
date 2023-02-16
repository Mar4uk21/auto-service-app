package com.example.carserviceapp.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanicRequestDto {
    private String fullName;
    private List<Long> completedOrdersId;
}
