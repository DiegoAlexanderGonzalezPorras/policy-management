package com.polizas.demo.dto;

import com.polizas.demo.enums.PolicyStatusEnum;
import com.polizas.demo.enums.PolicyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PolicyDto {
    private String policyNumber;
    private PolicyTypeEnum policyType;
    private PolicyStatusEnum status;
    private LocalDate startDate;
    private Integer policyDuration;
    private BigDecimal monthlyRent;
    private BigDecimal premiumAmount;
    private Boolean autoRenewal;
}
