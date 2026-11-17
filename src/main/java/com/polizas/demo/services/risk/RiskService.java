package com.polizas.demo.services.risk;

import com.polizas.demo.models.RiskEntity;
import com.polizas.demo.repositories.RiskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiskService implements IRiskService {
    private final RiskRepository riskRepository;

    @Override
    public void cancelRisk(Integer id){
        RiskEntity newRisk = riskRepository.findById(id).orElseThrow();
        newRisk.setActive(Boolean.FALSE);

        riskRepository.save(newRisk);
    }
}
