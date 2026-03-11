package org.example.factory;

import org.example.domains.satellites.Satellite;
import org.example.domains.satellites.SatelliteParam;
import org.example.domains.satellites.SatelliteType;

public interface SatelliteFactory {
    Satellite createSatellite(SatelliteParam satelliteParam);

    boolean isSatelliteTypeSupported(SatelliteType type);
}
