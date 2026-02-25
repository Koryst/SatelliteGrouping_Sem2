package org.example.factory.impl;

import org.example.domains.CommunicationSatellite;
import org.example.domains.Satellite;
import org.example.factory.SatelliteFactory;
import org.springframework.stereotype.Component;

@Component
public class CommunicationSatelliteFactory implements SatelliteFactory {
    private static final double DEFAULT_BANDWIDTH = 100.0;

    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new CommunicationSatellite(name, batteryLevel, DEFAULT_BANDWIDTH);
    }

    @Override
    public Satellite createSatellite(String name, double batteryLevel, double parameter) {
        return new  CommunicationSatellite(name, batteryLevel, parameter);
    }
}
