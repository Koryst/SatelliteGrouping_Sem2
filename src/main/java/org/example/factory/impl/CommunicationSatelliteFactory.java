package org.example.factory.impl;

import org.example.domains.satellites.*;
import org.example.factory.SatelliteFactory;
import org.springframework.stereotype.Component;

@Component
public class CommunicationSatelliteFactory implements SatelliteFactory {

    @Override
    public Satellite createSatellite(SatelliteParam param) {
        if (SatelliteType.COMMUNICATION.equals(param.getType())
                && param instanceof CommunicationSatelliteParam comParam) {
            return new CommunicationSatellite(
                    comParam.getName(),
                    comParam.getBatteryLevel(),
                    comParam.getBandwidth()
            );
        }

        throw new RuntimeException("Данный тип параметров не поддерживается");
    }

    @Override
    public boolean isSatelliteTypeSupported(SatelliteType type) {
        return SatelliteType.COMMUNICATION.equals(type);
    }
}
