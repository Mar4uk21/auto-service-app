package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.TypeServiceRequestDto;
import com.example.carserviceapp.dto.response.TypeServiceResponseDto;
import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.model.enums.OrderStatus;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.service.impl.MechanicServiceImpl;
import com.example.carserviceapp.service.impl.OrderServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TypeServiceMapperTest {
    private static final TypeServiceRequestDto TEST_TYPE_SERVICE_REQUEST_DTO =
            new TypeServiceRequestDto(1L,1L, BigDecimal.valueOf(1000), PaymentStatus.UNPAID);
    private static final Mechanic TEST_MECHANIC = new Mechanic(1L,"Igor",null);
    private static final Order TEST_ORDER = new Order(1L,null,"Change engine",
            LocalDateTime.now(),null,null,
            OrderStatus.ACCEPTED,BigDecimal.valueOf(5000),LocalDateTime.now());
    private static final TypeService TEST_TYPE_SERVICE = new TypeService(2L,TEST_ORDER,TEST_MECHANIC,BigDecimal.valueOf(4000),PaymentStatus.UNPAID);

    @InjectMocks
    private TypeServiceMapper mapper;

    @Mock
    private OrderServiceImpl orderService;

    @Mock
    private MechanicServiceImpl mechanicService;


    @Test
    void correctMappingTypeServiceRequestDtoToTypeService() {
        Mockito.when(orderService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getOrderId())).thenReturn(TEST_ORDER);
        Mockito.when(mechanicService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getMechanicId())).thenReturn(TEST_MECHANIC);
        TypeService expected = new TypeService(null,orderService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getOrderId()),
                mechanicService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getMechanicId()),
                TEST_TYPE_SERVICE_REQUEST_DTO.getPrice(),TEST_TYPE_SERVICE_REQUEST_DTO.getPaymentStatus());
        TypeService actual = mapper.mapToModel(TEST_TYPE_SERVICE_REQUEST_DTO);
        Assertions.assertEquals(actual.getOrder(), expected.getOrder());
        Assertions.assertEquals(actual.getMechanic(), expected.getMechanic());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
        Assertions.assertEquals(actual.getPaymentStatus(), expected.getPaymentStatus());
    }

    @Test
    void correctMappingTypeServiceToTypeServiceResponseDto() {
        TypeServiceResponseDto expected = new TypeServiceResponseDto(TEST_TYPE_SERVICE.getId(),TEST_TYPE_SERVICE.getOrder().getId(),
                TEST_TYPE_SERVICE.getMechanic().getId(),TEST_TYPE_SERVICE.getPrice(),TEST_TYPE_SERVICE.getPaymentStatus());
        TypeServiceResponseDto actual = mapper.mapToDto(TEST_TYPE_SERVICE);
        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getOrderId(), expected.getOrderId());
        Assertions.assertEquals(actual.getMechanicId(), expected.getMechanicId());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
        Assertions.assertEquals(actual.getPaymentStatus(), expected.getPaymentStatus());
    }
}
