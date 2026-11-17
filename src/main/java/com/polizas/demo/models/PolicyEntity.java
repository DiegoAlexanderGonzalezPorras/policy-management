package com.polizas.demo.models;

import com.polizas.demo.enums.PolicyStatusEnum;
import com.polizas.demo.enums.PolicyTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "policies")
public class PolicyEntity {
    @Id
    @Column(name = "policy_number", length = 50, nullable = false)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "policy_type", nullable = false)
    private PolicyTypeEnum policyType;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", nullable = false)
    private PolicyStatusEnum status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "policy_duration", nullable = false)
    private Integer policyDuration;

    @Column(name = "monthly_rent", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyRent;

    @Column(name = "premium_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal premiumAmount;

    @Column(name = "auto_renewal", nullable = false)
    private Boolean autoRenewal = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "policy_risks",
            joinColumns = @JoinColumn(name = "policy_number"),
            inverseJoinColumns = @JoinColumn(name = "id_risk")
    )
    private Set<RiskEntity> risks = new HashSet<>();
}