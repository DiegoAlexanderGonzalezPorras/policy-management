package com.polizas.demo.controllers;

import com.polizas.demo.dto.PolicyDto;
import com.polizas.demo.dto.PolicyRiskDto;
import com.polizas.demo.dto.RiskPolicyRqDto;
import com.polizas.demo.enums.PolicyStatusEnum;
import com.polizas.demo.enums.PolicyTypeEnum;
import com.polizas.demo.services.policy.IPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/polizas")
public class PolicyController {
    private final IPolicyService policyService;

    @GetMapping("")
    public ResponseEntity<List<PolicyDto>> getPoliciesByTypeAndStatus(
            @RequestParam("tipo") PolicyTypeEnum tipo,
            @RequestParam("estado") PolicyStatusEnum estado) {
        return new ResponseEntity<>(policyService.getPoliciesByTypeAndStatus(estado, tipo), HttpStatus.OK);
    }

    @GetMapping("/{id}/riesgos")
    public ResponseEntity<List<PolicyRiskDto>> getPoliciesRisks(
            @PathVariable("id") String id) {
        return new ResponseEntity<>(policyService.getPoliciesRisks(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<Boolean> renewPolicy(
            @PathVariable("id") String id) {
        try {
            policyService.renewPolicy(id);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Boolean> cancelPolicy(
            @PathVariable("id") String id) {
        try {
            policyService.cancelPolicy(id);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/riesgos")
    public ResponseEntity<Boolean> addRiskPolicy(
            @PathVariable("id") String id,
            @RequestBody RiskPolicyRqDto riskPolicyRqDto) {
        try {
            policyService.addRiskPolicy(id, riskPolicyRqDto);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
