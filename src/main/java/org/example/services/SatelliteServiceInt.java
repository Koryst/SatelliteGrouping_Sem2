package org.example.services;

import org.example.domains.satellites.Satellite;
import org.example.domains.satellites.SatelliteParam;

public interface SatelliteServiceInt {
    Satellite createSatellite(SatelliteParam param);
}
