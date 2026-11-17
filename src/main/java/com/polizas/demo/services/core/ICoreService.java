package com.polizas.demo.services.core;

import com.polizas.demo.dto.CoreRsDto;

public interface ICoreService {
    CoreRsDto sendCoreData(String policyId, Object body);
}
