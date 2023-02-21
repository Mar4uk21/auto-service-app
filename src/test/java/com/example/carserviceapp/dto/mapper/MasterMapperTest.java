package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.MasterRequestDto;
import com.example.carserviceapp.dto.response.MasterResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.model.Master;
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
class MasterMapperTest {
    private static final MasterRequestDto TEST_MASTER_REQUEST_DTO = new MasterRequestDto("Igor", List.of(1L,2L));
    private static final List<Order> TEST_COMPLETED_ORDERS = List.of(new Order(1L,new Car(),"Change engine", LocalDateTime.now(),
            Collections.emptyList(),Collections.emptyList(), OrderStatus.DURING, BigDecimal.valueOf(5000),LocalDateTime.now()));
    private static final Master TEST_MASTER = new Master(1L,"Vlad",TEST_COMPLETED_ORDERS);

    @InjectMocks
    private MasterMapper mapper;

    @Mock
    private OrderServiceImpl orderService;

    @Test
    void correctMappingMasterRequestDtoToMaster() {
        Mockito.when(orderService.getAllOrders(TEST_MASTER_REQUEST_DTO.getCompletedOrdersId()))
                .thenReturn(TEST_COMPLETED_ORDERS);
        Master expected = new Master(null,TEST_MASTER_REQUEST_DTO.getFullName(),
                orderService.getAllOrders(TEST_MASTER_REQUEST_DTO.getCompletedOrdersId()));
        Master actual = mapper.mapToModel(TEST_MASTER_REQUEST_DTO);
        Assertions.assertEquals(actual.getFullName(), expected.getFullName());
        Assertions.assertEquals(actual.getCompletedOrders(), expected.getCompletedOrders());
    }

    @Test
    void correctMappingMasterToMasterResponseDto() {
        MasterResponseDto expected = new MasterResponseDto(TEST_MASTER.getId(), TEST_MASTER.getFullName(),
                TEST_MASTER.getCompletedOrders().stream().map(Order::getId).collect(Collectors.toList()));
        MasterResponseDto actual = mapper.mapToDto(TEST_MASTER);
        Assertions.assertEquals(actual.getId(),expected.getId());
        Assertions.assertEquals(actual.getFullName(), expected.getFullName());
        Assertions.assertEquals(actual.getCompletedOrdersId(), expected.getCompletedOrdersId());
    }
}
