package com.example.carserviceapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private Long yearOfRelease;
    private String numberOfCar;
    private Long ownerId;
}
