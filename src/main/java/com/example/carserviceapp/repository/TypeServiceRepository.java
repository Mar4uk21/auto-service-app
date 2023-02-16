package com.example.carserviceapp.repository;

import com.example.carserviceapp.model.Mechanic;
import com.example.carserviceapp.model.TypeService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Long> {
    List<TypeService> findAllByMechanic(Mechanic mechanic);
}
