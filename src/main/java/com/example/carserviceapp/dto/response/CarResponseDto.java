package com.example.carserviceapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDto {
    private Long id;
    private String manufacturer;
    private String model;
    private Long yearOfRelease;
    private String numberOfCar;
    private Long ownerId;
}
