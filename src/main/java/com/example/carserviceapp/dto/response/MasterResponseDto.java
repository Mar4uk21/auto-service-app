package com.example.carserviceapp.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterResponseDto {
    private Long id;
    private String fullName;
    private List<Long> completedOrdersId;
}
