package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.CarRequestDto;
import com.example.carserviceapp.dto.response.CarResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.service.impl.CarOwnerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarMapperTest {
    private static final CarRequestDto TEST_CAR_REQUEST_DTO = new CarRequestDto("BMW","E40",2022L,"777",1L);
    private static final CarOwner TEST_OWNER = new CarOwner(1L,null,null);
    private static final Car TEST_CAR = new Car(2L,"AUDI","Q7",2023L,"666",TEST_OWNER);

    @InjectMocks
    private CarMapper mapper;
    @Mock
    private CarOwnerServiceImpl ownerService;

    @Test
    void correctMappingCarRequestDtoToCar() {
        Mockito.when(ownerService.get(TEST_CAR_REQUEST_DTO.getOwnerId())).thenReturn(TEST_OWNER);
        Car expected = new Car(null,TEST_CAR_REQUEST_DTO.getManufacturer(),
                TEST_CAR_REQUEST_DTO.getModel(),TEST_CAR_REQUEST_DTO.getYearOfRelease(),
                TEST_CAR_REQUEST_DTO.getNumberOfCar(),
                ownerService.get(TEST_CAR_REQUEST_DTO.getOwnerId()));
        Car actual = mapper.mapToModel(TEST_CAR_REQUEST_DTO);
        Assertions.assertEquals(actual.getManufacturer(), expected.getManufacturer());
        Assertions.assertEquals(actual.getModel(), expected.getModel());
        Assertions.assertEquals(actual.getYearOfRelease(), expected.getYearOfRelease());
        Assertions.assertEquals(actual.getNumberOfCar(), expected.getNumberOfCar());
        Assertions.assertEquals(actual.getOwner(), expected.getOwner());
    }

    @Test
    void correctMappingCarToCarResponseDto() {
        CarResponseDto expected = new CarResponseDto(TEST_CAR.getId(),TEST_CAR.getManufacturer(),
                TEST_CAR.getModel(),TEST_CAR.getYearOfRelease(),
                TEST_CAR.getNumberOfCar(),TEST_CAR.getOwner().getId());
        CarResponseDto actual = mapper.mapToDto(TEST_CAR);
        Assertions.assertEquals(actual.getId(),expected.getId());
        Assertions.assertEquals(actual.getManufacturer(), expected.getManufacturer());
        Assertions.assertEquals(actual.getModel(), expected.getModel());
        Assertions.assertEquals(actual.getYearOfRelease(), expected.getYearOfRelease());
        Assertions.assertEquals(actual.getNumberOfCar(), expected.getNumberOfCar());
        Assertions.assertEquals(actual.getOwnerId(), expected.getOwnerId());
    }
}
