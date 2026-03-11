package org.example.services;

import org.example.domains.satellites.Satellite;
import org.example.domains.satellites.SatelliteConstellation;
import org.example.repository.ConstellationRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConstellationService {
    private final ConstellationRepository repository;

    public ConstellationService(ConstellationRepository repository) {
        this.repository = repository;
    }

    public void createAndSaveConstellation(String name) {
        SatelliteConstellation constellation = new SatelliteConstellation(name);
        repository.addConstellation(constellation);
    }

    public void addSatelliteToConstellation(String constellationName, Satellite satellite) {
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        constellation.addSatellite(satellite);
        System.out.println("Добавлен спутник " + satellite.getName() +
                " в группировку " + constellationName);
    }

    public void executeConstellationMission(String constellationName) {
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        System.out.println("\n=== ВЫПОЛНЕНИЕ МИССИЙ ДЛЯ ГРУППИРОВКИ: " + constellationName + " ===");
        constellation.executeAllMissions();
    }

    public void activateAllSatellites(String constellationName) {
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        System.out.println("\n=== АКТИВАЦИЯ СПУТНИКОВ В ГРУППИРОВКЕ: " + constellationName + " ===");

        for (Satellite satellite : constellation.getSatellites()) {
            satellite.activate();
        }
    }

    public void showConstellationStatus(String constellationName) {
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        System.out.println("\n=== СТАТУС ГРУППИРОВКИ: " + constellationName + " ===");

        System.out.println("Количество спутников: " + constellation.getSatellites().size());

        for (Satellite satellite : constellation.getSatellites()) {
            System.out.println(satellite.getState());
        }
    }

    public SatelliteConstellation getConstellation(String constellationName) {
        return repository.getConstellation(constellationName);
    }

    public Map<String, SatelliteConstellation> getConstellations() {
        return repository.getConstellations();
    }
}
