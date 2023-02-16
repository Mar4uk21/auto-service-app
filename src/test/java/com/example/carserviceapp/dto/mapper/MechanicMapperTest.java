package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.MechanicRequestDto;
import com.example.carserviceapp.dto.response.MechanicResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.enums.OrderStatus;
import com.example.carserviceapp.service.impl.OrderServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MechanicMapperTest {
    private static final MechanicRequestDto TEST_MECHANIC_REQUEST_DTO = new MechanicRequestDto("Igor", List.of(1L,2L));
    private static final List<Order> TEST_COMPLETED_ORDERS = List.of(new Order(1L,new Car(),"Change engine", LocalDateTime.now(),
            Collections.emptyList(),Collections.emptyList(), OrderStatus.DURING, BigDecimal.valueOf(5000),LocalDateTime.now()));
    private static final Mechanic TEST_MECHANIC = new Mechanic(1L,"Vlad",TEST_COMPLETED_ORDERS);

    @InjectMocks
    private MechanicMapper mapper;

    @Mock
    private OrderServiceImpl orderService;

    @Test
    void correctMappingMechanicRequestDtoToMechanic() {
        Mockito.when(orderService.getAllOrders(TEST_MECHANIC_REQUEST_DTO.getCompletedOrdersId()))
                .thenReturn(TEST_COMPLETED_ORDERS);
        Mechanic expected = new Mechanic(null,TEST_MECHANIC_REQUEST_DTO.getFullName(),
                orderService.getAllOrders(TEST_MECHANIC_REQUEST_DTO.getCompletedOrdersId()));
        Mechanic actual = mapper.mapToModel(TEST_MECHANIC_REQUEST_DTO);
        Assertions.assertEquals(actual.getFullName(), expected.getFullName());
        Assertions.assertEquals(actual.getCompletedOrders(), expected.getCompletedOrders());
    }

    @Test
    void correctMappingMechanicToMechanicResponseDto() {
        MechanicResponseDto expected = new MechanicResponseDto(TEST_MECHANIC.getId(),TEST_MECHANIC.getFullName(),
                TEST_MECHANIC.getCompletedOrders().stream().map(Order::getId).collect(Collectors.toList()));
        MechanicResponseDto actual = mapper.mapToDto(TEST_MECHANIC);
        Assertions.assertEquals(actual.getId(),expected.getId());
        Assertions.assertEquals(actual.getFullName(), expected.getFullName());
        Assertions.assertEquals(actual.getCompletedOrdersId(), expected.getCompletedOrdersId());
    }
}
