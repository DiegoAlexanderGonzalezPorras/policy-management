package com.polizas.demo.services.policy;

import com.polizas.demo.dto.CoreRqDto;
import com.polizas.demo.dto.PolicyDto;
import com.polizas.demo.dto.PolicyRiskDto;
import com.polizas.demo.dto.RiskPolicyRqDto;
import com.polizas.demo.enums.ErrorEnum;
import com.polizas.demo.enums.PolicyStatusEnum;
import com.polizas.demo.enums.PolicyTypeEnum;
import com.polizas.demo.models.PolicyEntity;
import com.polizas.demo.models.RiskEntity;
import com.polizas.demo.repositories.PolicyRepository;
import com.polizas.demo.repositories.RiskRepository;
import com.polizas.demo.services.core.ICoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PolicyService implements IPolicyService{
    @Value("${ipc.value}")
    private float ipcValue;

    private final PolicyRepository policyRepository;
    private final RiskRepository riskRepository;
    private final ICoreService coreService;

    @Override
    public List<PolicyDto> getPoliciesByTypeAndStatus(PolicyStatusEnum status, PolicyTypeEnum type) {
        return policyRepository.findByPolicyTypeAndStatus(type, status)
                .stream()
                .map(element -> new PolicyDto(
                        element.getPolicyNumber(),
                        element.getPolicyType(),
                        element.getStatus(),
                        element.getStartDate(),
                        element.getPolicyDuration(),
                        element.getMonthlyRent(),
                        element.getPremiumAmount(),
                        element.getAutoRenewal()
                ))
                .toList();
    }

    @Override
    public List<PolicyRiskDto> getPoliciesRisks(String id) {
        return policyRepository.findById(id)
                .stream()
                .map(element -> new PolicyRiskDto(
                        element.getPolicyNumber(),
                        element.getPolicyType(),
                        element.getRisks()
                ))
                .toList();
    }

    @Override
    public void renewPolicy(String id) throws Exception {
        PolicyEntity newPolicy = policyRepository.findByPolicyNumber(id).orElseThrow();

        if(!newPolicy.getStatus().equals(PolicyStatusEnum.CANCELADA)){
            BigDecimal upRate = BigDecimal.valueOf(1 + ipcValue);

            newPolicy.setMonthlyRent(newPolicy.getMonthlyRent().multiply(upRate));
            newPolicy.setPremiumAmount(newPolicy.getPremiumAmount().multiply(upRate));
            newPolicy.setStatus(PolicyStatusEnum.RENOVADA);

            policyRepository.save(newPolicy);

            coreService.sendCoreData(id, new CoreRqDto("RENOVACION", id));
        } else {
            throw new Exception(String.valueOf(ErrorEnum.CANCEL_POLICY));
        }

    }

    @Override
    public void cancelPolicy(String id){
        PolicyEntity newPolicy = policyRepository.findByPolicyNumber(id).orElseThrow();
        newPolicy.setStatus(PolicyStatusEnum.CANCELADA);

        policyRepository.save(newPolicy);

        coreService.sendCoreData(id, new CoreRqDto("CANCELACION", id));
    }

    @Override
    public void addRiskPolicy(String id, RiskPolicyRqDto riskPolicyRqDto) throws Exception {
        PolicyEntity newPolicy = policyRepository.findByPolicyNumber(id).orElseThrow();

        if (newPolicy.getPolicyType().equals(PolicyTypeEnum.COLECTIVA)){
            RiskEntity newRisk = riskRepository.findById(riskPolicyRqDto.getRiskId()).orElseThrow();

            Set<RiskEntity> risks = newPolicy.getRisks();
            risks.add(newRisk);

            newPolicy.setRisks(risks);

            policyRepository.save(newPolicy);

            coreService.sendCoreData(id, new CoreRqDto("AGREGAR RIESGO", id));
        } else {
            throw new Exception(String.valueOf(ErrorEnum.INDIVIDUAL_POLICY));
        }

    }
}
