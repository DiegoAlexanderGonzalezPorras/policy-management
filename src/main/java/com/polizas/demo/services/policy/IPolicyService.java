package com.polizas.demo.services.policy;

import com.polizas.demo.dto.PolicyDto;
import com.polizas.demo.dto.PolicyRiskDto;
import com.polizas.demo.dto.RiskPolicyRqDto;
import com.polizas.demo.enums.PolicyStatusEnum;
import com.polizas.demo.enums.PolicyTypeEnum;

import java.util.List;

public interface IPolicyService {
    List<PolicyDto> getPoliciesByTypeAndStatus(PolicyStatusEnum status, PolicyTypeEnum type);

    List<PolicyRiskDto> getPoliciesRisks(String id);

    void renewPolicy(String id) throws Exception;

    void cancelPolicy(String id);

    void addRiskPolicy(String id, RiskPolicyRqDto riskPolicyRqDto) throws Exception;
}
