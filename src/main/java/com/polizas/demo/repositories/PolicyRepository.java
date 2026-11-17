package com.polizas.demo.repositories;

import com.polizas.demo.enums.PolicyStatusEnum;
import com.polizas.demo.enums.PolicyTypeEnum;
import com.polizas.demo.models.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<PolicyEntity, String> {
    List<PolicyEntity> findByPolicyTypeAndStatus(PolicyTypeEnum type, PolicyStatusEnum status);

    Optional<PolicyEntity> findByPolicyNumber(String policyNumber);
}
