package org.example.factory.impl;

import org.example.domains.ImagingSatellite;
import org.example.domains.Satellite;
import org.example.factory.SatelliteFactory;
import org.springframework.stereotype.Component;

@Component
public class ImagingSatelliteFactory implements SatelliteFactory {
    private static final double DEFAULT_RESOLUTION = 10.0;

    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new ImagingSatellite(name, batteryLevel, DEFAULT_RESOLUTION);
    }

    @Override
    public Satellite createSatellite(String name, double batteryLevel, double parameter) {
        return new ImagingSatellite(name, batteryLevel, parameter);
    }
}
