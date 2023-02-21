package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.enums.PaymentStatus;
import com.example.carserviceapp.repository.MasterRepository;
import com.example.carserviceapp.repository.TypeServiceRepository;
import com.example.carserviceapp.service.MasterService;
import com.example.carserviceapp.service.TypeServiceService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final TypeServiceRepository typeServiceRepository;
    private final TypeServiceService typeServiceService;

    public MasterServiceImpl(MasterRepository masterRepository,
                             TypeServiceRepository typeServiceRepository,
                             TypeServiceService typeServiceService) {
        this.masterRepository = masterRepository;
        this.typeServiceRepository = typeServiceRepository;
        this.typeServiceService = typeServiceService;
    }

    @Override
    public Master save(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master update(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master get(Long id) {
        return masterRepository.getById(id);
    }

    @Override
    public List<Order> getOrdersList(Master master) {
        return masterRepository.getById(master.getId()).getCompletedOrders();
    }

    @Override
    public BigDecimal calculateSalary(Long id) {
        BigDecimal salary = typeServiceRepository.findAllByMaster(masterRepository.getById(id))
                .stream()
                .filter(s -> s.getPaymentStatus().equals(PaymentStatus.UNPAID))
                .map(s -> s.getPrice().multiply(BigDecimal.valueOf(0.4)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        typeServiceRepository.findAllByMaster(masterRepository.getById(id))
                .stream()
                .filter(s -> s.getPaymentStatus().equals(PaymentStatus.UNPAID))
                .peek(s -> s.setPaymentStatus(PaymentStatus.PAID))
                .map(typeServiceService::update)
                .toList();
        return salary;
    }
}
