package org.example.repository;

import org.example.domains.ImagingSatellite;
import org.example.domains.ImagingSatelliteParam;
import org.example.domains.Satellite;
import org.example.domains.SatelliteParam;
import org.example.services.SatelliteServiceInt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Тесты сервисов спутников")
public class SatelliteServiceImplTest {
    private static final String NAME = "TestSatellite";
    private static final Double BATTERY_LEVEL = 0.6;
    private static final Double PARAM = 0.1;

    @Autowired
    private SatelliteServiceInt satelliteService;

    @Test
    @DisplayName("Добавление нескольких группировок должно сохранять их все")
    void addAllConstellationTest() {
        SatelliteParam imgParam = new ImagingSatelliteParam(NAME, BATTERY_LEVEL, PARAM);

        Satellite satellite = satelliteService.createSatellite(imgParam);

        assertNotNull(satellite);
        assertInstanceOf(ImagingSatellite.class, satellite);

        ImagingSatellite imagingSatellite = (ImagingSatellite) satellite;

        assertEquals(NAME, imagingSatellite.getName());
        assertEquals(BATTERY_LEVEL, imagingSatellite.getEnergy().getBatteryLevel());
        assertEquals(PARAM, imagingSatellite.getResolution());

    }
}
