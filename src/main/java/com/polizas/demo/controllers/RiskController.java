package com.polizas.demo.controllers;

import com.polizas.demo.services.risk.IRiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/riesgos")
public class RiskController {
    private final IRiskService riskService;

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Boolean> cancelRisk(
            @PathVariable("id") Integer id) {
        try {
            riskService.cancelRisk(id);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
