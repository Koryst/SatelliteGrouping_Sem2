package org.example.repository;

import org.example.SatelliteConstellation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConstellationRepository {
    private final Map<String, SatelliteConstellation> constellations = new HashMap<>();

    public void addConstellation(SatelliteConstellation constellation) {
        constellations.put(constellation.getConstellationName(), constellation);
        System.out.println("Сохранена группировка " + constellation.getConstellationName());
    }

    public SatelliteConstellation getConstellation(String name) {
        SatelliteConstellation constellation = constellations.get(name);
        if (constellation == null) {
            throw new RuntimeException("Группировка не найдена: " + name);
        }
        return constellation;
    }

    public Map<String, SatelliteConstellation> getConstellations() {
        return new HashMap<>(constellations);
    }

    public boolean containsConstellation(String name) {
        return constellations.containsKey(name);
    }

    public void removeConstellation(String name) {
        constellations.remove(name);
        System.out.println("Удалена группировка: " + name);
    }
}
