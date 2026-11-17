package com.polizas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoreRqDto {
    private String evento;
    private String polizaId;
}
