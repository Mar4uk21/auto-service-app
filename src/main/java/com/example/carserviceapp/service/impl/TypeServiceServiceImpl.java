package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.TypeService;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.repository.TypeServiceRepository;
import com.example.carserviceapp.service.TypeServiceService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceServiceImpl implements TypeServiceService {
    private final TypeServiceRepository typeServiceRepository;

    public TypeServiceServiceImpl(TypeServiceRepository typeServiceRepository) {
        this.typeServiceRepository = typeServiceRepository;
    }

    @Override
    public TypeService save(TypeService typeService) {
        return typeServiceRepository.save(typeService);
    }

    @Override
    public TypeService update(TypeService typeService) {
        return typeServiceRepository.save(typeService);
    }

    @Override
    public TypeService get(Long id) {
        return typeServiceRepository.getById(id);
    }

    @Override
    public void changePaymentStatus(Long id) {
        TypeService typeService = typeServiceRepository.getById(id);
        if (typeService.getPaymentStatus().equals(PaymentStatus.PAID)) {
            typeService.setPaymentStatus(PaymentStatus.UNPAID);
        } else {
            typeService.setPaymentStatus(PaymentStatus.PAID);
        }
    }

    @Override
    public List<TypeService> getAllTypeServices(List<Long> typeServicesIds) {
        return typeServiceRepository.findAllById(typeServicesIds);
    }
}
