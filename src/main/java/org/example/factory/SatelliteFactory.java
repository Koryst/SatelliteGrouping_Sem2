package org.example.factory;

import org.example.domains.Satellite;
import org.example.domains.SatelliteParam;
import org.example.domains.SatelliteType;

public interface SatelliteFactory {
    Satellite createSatellite(SatelliteParam satelliteParam);

    boolean isSatelliteTypeSupported(SatelliteType type);
}
