package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.repository.MechanicRepository;
import com.example.carserviceapp.repository.TypeServiceRepository;
import com.example.carserviceapp.service.MechanicService;
import com.example.carserviceapp.service.TypeServiceService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;
    private final TypeServiceRepository typeServiceRepository;
    private final TypeServiceService typeServiceService;

    public MechanicServiceImpl(MechanicRepository mechanicRepository,
                               TypeServiceRepository typeServiceRepository,
                               TypeServiceService typeServiceService) {
        this.mechanicRepository = mechanicRepository;
        this.typeServiceRepository = typeServiceRepository;
        this.typeServiceService = typeServiceService;
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic update(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic get(Long id) {
        return mechanicRepository.getById(id);
    }

    @Override
    public List<Order> getOrdersList(Mechanic mechanic) {
        return mechanicRepository.getById(mechanic.getId()).getCompletedOrders();
    }

    @Override
    public BigDecimal calculateSalary(Long id) {
        BigDecimal salary = typeServiceRepository.findAllByMechanic(mechanicRepository.getById(id))
                .stream()
                .filter(s -> s.getPaymentStatus().equals(PaymentStatus.UNPAID))
                .map(s -> s.getPrice().multiply(BigDecimal.valueOf(0.4)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        typeServiceRepository.findAllByMechanic(mechanicRepository.getById(id))
                .stream()
                .filter(s -> s.getPaymentStatus().equals(PaymentStatus.UNPAID))
                .peek(s -> s.setPaymentStatus(PaymentStatus.PAID))
                .map(typeServiceService::update)
                .toList();
        return salary;
    }
}
