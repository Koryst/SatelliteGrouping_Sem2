package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.domains.Satellite;
import org.example.domains.SatelliteParam;
import org.example.factory.SatelliteFactory;
import org.example.services.SatelliteServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SatelliteServiceImpl implements SatelliteServiceInt {
    private final List<SatelliteFactory> factories;

    @Override
    public Satellite createSatellite(SatelliteParam param) {
        SatelliteFactory factory = factories.stream()
                .filter(satelliteFactory -> satelliteFactory
                        .isSatelliteTypeSupported(param.getType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Данный тип параметров не поддерживается"));

        return factory.createSatellite(param);
    }
}
