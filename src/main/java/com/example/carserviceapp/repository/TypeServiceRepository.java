package com.example.carserviceapp.repository;

import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.TypeService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Long> {
    List<TypeService> findAllByMaster(Master master);
}
