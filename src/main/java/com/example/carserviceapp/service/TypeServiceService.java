package com.example.carserviceapp.service;

import com.example.carserviceapp.model.TypeService;
import java.util.List;

public interface TypeServiceService {
    TypeService save(TypeService typeService);

    TypeService update(TypeService typeService);

    TypeService get(Long id);

    void changePaymentStatus(Long id);

    List<TypeService> getAllTypeServices(List<Long> typeServicesIds);
}
