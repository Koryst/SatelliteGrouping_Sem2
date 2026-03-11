package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.aop.LogExecutionTime;
import org.example.domains.requests.AddSatelliteRequest;
import org.example.domains.requests.MissionRequest;
import org.example.domains.satellites.Satellite;
import org.example.domains.satellites.SatelliteParam;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpaceOperationCenterService {
    private final ConstellationService constellationService;
    private final SatelliteServiceInt satelliteService;

    @LogExecutionTime
    public void addSetellite(AddSatelliteRequest request) {
        try {
            constellationService.showConstellationStatus(request.constellationName());
        } catch (Exception e) {
            constellationService.createAndSaveConstellation(request.constellationName());
        }

        for (SatelliteParam param : request.satelliteParams()) {
            Satellite satellite = satelliteService.createSatellite(param);
            constellationService.addSatelliteToConstellation(request.constellationName(), satellite);
        }
    }

     public void executeMission(MissionRequest request) {
        switch (request.targetType()) {
            case CONSTELLATION -> {
                constellationService.activateAllSatellites(request.constellationName());
                constellationService.executeConstellationMission(request.constellationName());
            }
            case SINGLE_SATELLITE -> {
                var constellation = constellationService.getConstellation(request.constellationName());
                var satellite = constellation.getSatellites().stream()
                        .filter(s ->s.getName().equals(request.satelliteName()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Спутник не найден: " + request.satelliteName()));
                satellite.activate();
                satellite.performMission();
            }
        }
     }

     public String getSystemOverview() {
        var allConstellation = constellationService.getConstellations();
        StringBuilder sb = new StringBuilder("== СИСТЕМНАЯ СВОДКА ===\n");
        sb.append("Всего группировок: ").append(allConstellation.size()).append("\n");
        allConstellation.values().forEach(cons -> {
            sb.append(" Группировка '").append(cons.getConstellationName())
                    .append("': спутников ").append(cons.getSatellites().size()).append("\n");
            cons.getSatellites().forEach(sat -> {
                sb.append("    - ").append(sat.getName())
                        .append(" [").append(sat.getState().isActive() ? "Активен" : "Неактивен")
                        .append("], заряд: ").append((int)(sat.getEnergy().getBatteryLevel()*100)).append("%\n");
            });
        });
        return sb.toString();
     }
}
