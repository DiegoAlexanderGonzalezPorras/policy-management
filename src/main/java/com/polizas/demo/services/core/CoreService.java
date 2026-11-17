package com.polizas.demo.services.core;

import com.polizas.demo.configurations.RestClientConfig;
import com.polizas.demo.dto.CoreRsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreService implements ICoreService{
    private final RestClient restClient;

    @Override
    public CoreRsDto sendCoreData(String policyId, Object body) {
        log.info("Enviar datos para poliza {}", policyId);

        return restClient.post()
                .uri("/evento")
                .body(body)
                .retrieve()
                .body(CoreRsDto.class);
    }
}
