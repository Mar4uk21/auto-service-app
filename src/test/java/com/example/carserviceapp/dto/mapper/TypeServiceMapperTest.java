package com.example.carserviceapp.dto.mapper;

import com.example.carserviceapp.dto.request.TypeServiceRequestDto;
import com.example.carserviceapp.dto.response.TypeServiceResponseDto;
import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.model.enums.OrderStatus;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.service.impl.MasterServiceImpl;
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
            new TypeServiceRequestDto(1L,1L, BigDecimal.valueOf(1000));
    private static final Master TEST_MASTER = new Master(1L,"Igor",null);
    private static final Order TEST_ORDER = new Order(1L,null,"Change engine",
            LocalDateTime.now(),null,null,
            OrderStatus.ACCEPTED,BigDecimal.valueOf(5000),LocalDateTime.now());
    private static final TypeService TEST_TYPE_SERVICE = new TypeService(2L,TEST_ORDER, TEST_MASTER,BigDecimal.valueOf(4000),PaymentStatus.UNPAID);

    @InjectMocks
    private TypeServiceMapper mapper;

    @Mock
    private OrderServiceImpl orderService;

    @Mock
    private MasterServiceImpl masterService;


    @Test
    void correctMappingTypeServiceRequestDtoToTypeService() {
        Mockito.when(orderService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getOrderId())).thenReturn(TEST_ORDER);
        Mockito.when(masterService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getMasterId())).thenReturn(TEST_MASTER);
        TypeService expected = new TypeService(null,orderService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getOrderId()),
                masterService.get(TEST_TYPE_SERVICE_REQUEST_DTO.getMasterId()),
                TEST_TYPE_SERVICE_REQUEST_DTO.getPrice(),null);
        TypeService actual = mapper.mapToModel(TEST_TYPE_SERVICE_REQUEST_DTO);
        Assertions.assertEquals(actual.getOrder(), expected.getOrder());
        Assertions.assertEquals(actual.getMaster(), expected.getMaster());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
        Assertions.assertEquals(actual.getPaymentStatus(), expected.getPaymentStatus());
    }

    @Test
    void correctMappingTypeServiceToTypeServiceResponseDto() {
        TypeServiceResponseDto expected = new TypeServiceResponseDto(TEST_TYPE_SERVICE.getId(),TEST_TYPE_SERVICE.getOrder().getId(),
                TEST_TYPE_SERVICE.getMaster().getId(),TEST_TYPE_SERVICE.getPrice(),TEST_TYPE_SERVICE.getPaymentStatus());
        TypeServiceResponseDto actual = mapper.mapToDto(TEST_TYPE_SERVICE);
        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getOrderId(), expected.getOrderId());
        Assertions.assertEquals(actual.getMasterId(), expected.getMasterId());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
        Assertions.assertEquals(actual.getPaymentStatus(), expected.getPaymentStatus());
    }
}
