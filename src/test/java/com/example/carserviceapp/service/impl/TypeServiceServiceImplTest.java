package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.repository.TypeServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TypeServiceServiceImplTest {
    @InjectMocks
    private TypeServiceServiceImpl typeServiceService;

    @Mock
    private TypeServiceRepository typeServiceRepository;

    @Test
    void shouldReturnValidPaymentStatusPaid() {
        Mockito.when(typeServiceRepository.getById(1L)).thenReturn(new TypeService(1L, new Order(), new Mechanic(),
                BigDecimal.valueOf(1000), PaymentStatus.UNPAID));
        TypeService actual = typeServiceService.get(1L);
        typeServiceService.changePaymentStatus(actual.getId());
        Assertions.assertEquals(PaymentStatus.PAID,actual.getPaymentStatus());
    }

    @Test
    void shouldReturnValidPaymentStatusUnpaid() {
        Mockito.when(typeServiceRepository.getById(1L)).thenReturn(new TypeService(1L, new Order(), new Mechanic(),
                BigDecimal.valueOf(1000), PaymentStatus.PAID));
        TypeService actual = typeServiceService.get(1L);
        typeServiceService.changePaymentStatus(actual.getId());
        Assertions.assertEquals(PaymentStatus.UNPAID,actual.getPaymentStatus());
    }
}
