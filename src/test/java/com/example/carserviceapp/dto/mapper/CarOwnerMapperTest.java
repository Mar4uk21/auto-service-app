package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.CarOwnerRequestDto;
import com.example.carserviceapp.dto.response.CarOwnerResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarOwnerMapperTest {
    private static final CarOwnerRequestDto TEST_CAR_OWNER_REQUEST_DTO = new CarOwnerRequestDto();
    private static final List<Car> TEST_CARS = List.of(new Car(1L,"BMW","E40",2023L,"666",new CarOwner()));
    private static final List<Order> TEST_ORDERS = List.of(new Order(1L,new Car(),"Change engine", LocalDateTime.now(),
            Collections.emptyList(),Collections.emptyList(), OrderStatus.DURING, BigDecimal.valueOf(5000),LocalDateTime.now()));
    private static final CarOwner TEST_CAR_OWNER = new CarOwner(1L,TEST_CARS,TEST_ORDERS);

    @InjectMocks
    private CarOwnerMapper carOwnerMapper;

    @Test
    void correctMappingCarOwnerRequestDtoToCarOwner() {
        CarOwner expected = new CarOwner(null,null, null);
        CarOwner actual = carOwnerMapper.mapToModel(TEST_CAR_OWNER_REQUEST_DTO);
        Assertions.assertEquals(actual.getCars(), expected.getCars());
        Assertions.assertEquals(actual.getOrders(), expected.getOrders());
    }

    @Test
    void correctMappingCarOwnerToCarOwnerResponseDto() {

        CarOwnerResponseDto expected = new CarOwnerResponseDto(TEST_CAR_OWNER.getId(),
                TEST_CAR_OWNER.getCars().stream().map(Car::getId).collect(Collectors.toList()),
                TEST_CAR_OWNER.getOrders().stream().map(Order::getId).collect(Collectors.toList()));
        CarOwnerResponseDto actual = carOwnerMapper.mapToDto(TEST_CAR_OWNER);
        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getCarsId(), expected.getCarsId());
        Assertions.assertEquals(actual.getOrdersId(), expected.getOrdersId());
    }
}
