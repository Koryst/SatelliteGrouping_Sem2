package org.example.domains.requests;

import org.example.domains.satellites.SatelliteParam;

import java.util.List;

public record AddSatelliteRequest (String constellationName, List<SatelliteParam> satelliteParams) {}
