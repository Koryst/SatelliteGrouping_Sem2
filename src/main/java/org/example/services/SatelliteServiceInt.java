package org.example.services;

import org.example.domains.Satellite;
import org.example.domains.SatelliteParam;

public interface SatelliteServiceInt {
    Satellite createSatellite(SatelliteParam param);
}
