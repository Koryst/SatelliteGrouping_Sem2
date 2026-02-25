package org.example.repository;

import org.example.domains.SatelliteConstellation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тест выполнения репозитория")
class ConstellationRepositoryUnitTest {
    private static final String CONSTELLATION_1 = "testConstellation1";
    private static final String CONSTELLATION_2 = "testConstellation2";

    private ConstellationRepository repository;

    @BeforeEach
    void setup() {
        repository = new ConstellationRepository();
    }

    @Test
    void testRepository() {
        SatelliteConstellation constellation1 = new SatelliteConstellation(CONSTELLATION_1);
        SatelliteConstellation constellation2 = new SatelliteConstellation(CONSTELLATION_2);

        repository.addConstellation(constellation1);
        repository.addConstellation(constellation2);

        assertTrue(repository.containsConstellation(CONSTELLATION_1));
        assertTrue(repository.containsConstellation(CONSTELLATION_2));

        assertEquals(2, repository.getConstellations().size());
    }
}
