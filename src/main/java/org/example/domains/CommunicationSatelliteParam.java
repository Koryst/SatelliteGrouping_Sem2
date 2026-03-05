package org.example.domains;

import lombok.Getter;

@Getter
public class CommunicationSatelliteParam extends SatelliteParam {
    private double bandwidth;

    public CommunicationSatelliteParam(String name, double batteryLevel, double bandwidth) {
        super(SatelliteType.COMMUNICATION, name, bandwidth);
        this.bandwidth = bandwidth;
    }
}
