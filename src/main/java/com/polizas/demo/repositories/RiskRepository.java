package com.polizas.demo.repositories;

import com.polizas.demo.models.RiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskRepository extends JpaRepository<RiskEntity, Integer> {
}
