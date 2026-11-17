package com.polizas.demo.dto;

import com.polizas.demo.enums.PolicyTypeEnum;
import com.polizas.demo.models.RiskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class PolicyRiskDto {
    private String policyNumber;
    private PolicyTypeEnum policyType;
    private Set<RiskEntity> risks;
}
