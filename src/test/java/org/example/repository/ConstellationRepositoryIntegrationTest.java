package org.example.repository;

import org.example.SatelliteConstellation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Тест выполнения репозитория")
public class ConstellationRepositoryIntegrationTest
{
    private static final String CONSTELLATION_1 = "testConstellation1";
    private static final String CONSTELLATION_2 = "testConstellation2";

    @Autowired
    private ConstellationRepository repository;

    @Test
    void testRepository() {
        SatelliteConstellation constellation1 = new SatelliteConstellation(CONSTELLATION_1);
        SatelliteConstellation constellation2 = new SatelliteConstellation(CONSTELLATION_2);

        Map<String, SatelliteConstellation> constellations = Map.of(
                CONSTELLATION_1, constellation1,
                CONSTELLATION_2, constellation2
        );

        repository.addConstellation(constellation1);
        repository.addConstellation(constellation2);

        assertTrue(repository.containsConstellation(CONSTELLATION_1));
        assertTrue(repository.containsConstellation(CONSTELLATION_2));

        assertEquals(2, repository.getConstellations().size());
    }
}
