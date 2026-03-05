package org.example.factory.impl;

import org.example.domains.*;
import org.example.factory.SatelliteFactory;
import org.springframework.stereotype.Component;

@Component
public class ImagingSatelliteFactory implements SatelliteFactory {
    @Override
    public Satellite createSatellite(SatelliteParam param) {
        if (SatelliteType.IMAGE.equals(param.getType())
                && param instanceof ImagingSatelliteParam imagingParam) {
            return new ImagingSatellite(
                    imagingParam.getName(),
                    imagingParam.getBatteryLevel(),
                    imagingParam.getResolution()
            );
        }

        throw new RuntimeException("Данный тип параметров не поддерживается");
    }

    @Override
    public boolean isSatelliteTypeSupported(SatelliteType type) {
        return SatelliteType.IMAGE.equals(type);
    }
}
