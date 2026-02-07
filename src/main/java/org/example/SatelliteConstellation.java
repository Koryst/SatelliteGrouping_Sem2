package org.example;

import java.util.ArrayList;
import java.util.List;

public class SatelliteConstellation {
    private final String constellationName;
    private final List<Satellite> satellites = new ArrayList<>();

    public SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
        System.out.println("Создана спутниковая группировка: " + constellationName);
    }

    public String getConstellationName() { return constellationName; }
    public List<Satellite> getSatellites() { return new ArrayList<>(satellites); }

    public void addSatellite(Satellite satellite) {
        if (satellite != null  && !satellites.contains(satellite)) {
            satellites.add(satellite);
            System.out.println(satellite.getName() + " добавлен в группировку '" + constellationName + "'");
        }
    }

    public void executeAllMissions() {
        System.out.println("ВЫПОЛНЕНиЕ МИССИЙ ГРУППИРОВКИ " + constellationName.toUpperCase());
        System.out.println("=".repeat(50));

        for (Satellite satellite : satellites) {
            satellite.performMission();
        }
    }

    @Override
    public String toString() {
        return "SatelliteConstellation{" +
                "name='" + constellationName + "'," +
                "satellites=[" + satellites + "]}";
    }
}