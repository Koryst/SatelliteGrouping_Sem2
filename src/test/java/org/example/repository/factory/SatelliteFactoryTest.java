package org.example.repository.factory;

import org.example.domains.CommunicationSatellite;
import org.example.domains.ImagingSatellite;
import org.example.domains.Satellite;
import org.example.factory.SatelliteFactory;
import org.example.factory.impl.CommunicationSatelliteFactory;
import org.example.factory.impl.ImagingSatelliteFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты фабрик спутников")
public class SatelliteFactoryTest {
    private static CommunicationSatelliteFactory communicationSatelliteFactory;
    private static ImagingSatelliteFactory imagingSatelliteFactory;

    @BeforeAll
    static void setUp() {
        communicationSatelliteFactory = new CommunicationSatelliteFactory();
        imagingSatelliteFactory = new ImagingSatelliteFactory();
    }

    @Test
    @DisplayName("Фабрика спутников связи создаёт спутник")
    void communicationFactoryCreateSatelliteFactory() {
        String name = "КомСат-1";
        double batteryLevel = 0.8;

        Satellite satellite = communicationSatelliteFactory.createSatellite(name, batteryLevel);

        assertNotNull(satellite);
        assertInstanceOf(CommunicationSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel());

        CommunicationSatellite commSatellite = (CommunicationSatellite) satellite;
        assertEquals(100.0, commSatellite.getBandwidth(), 0.001);
    }

    @Test
    @DisplayName("Фабрика спутников фотографов создаёт спутник")
    void imagingSatelliteFactoryCreateSatelliteFactory() {
        String name = "ДЗЗ-Сат-1";
        double batteryLevel = 0.7;

        Satellite satellite = imagingSatelliteFactory.createSatellite(name, batteryLevel);

        assertNotNull(satellite);
        assertInstanceOf(ImagingSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel());

        ImagingSatellite imagingSatellite = (ImagingSatellite) satellite;
        assertEquals(10.0, imagingSatellite.getResolution(), 0.001);
        assertEquals(0, imagingSatellite.getPhotosTaken());

    }

    @Test
    @DisplayName("Фабрики реализуют общий интерфейс SatelliteFactory")
    void factoriesImplementCommonInterface() {
        SatelliteFactory factory1 = communicationSatelliteFactory;
        SatelliteFactory factory2 = imagingSatelliteFactory;

        Satellite satellite1 = factory1.createSatellite("Сат1", 0.8);
        Satellite satellite2 = factory2.createSatellite("Сат2", 0.8);

        assertNotNull(satellite1);
        assertNotNull(satellite2);
        assertInstanceOf(Satellite.class, satellite1);
        assertInstanceOf(Satellite.class, satellite2);
    }

    @Test
    @DisplayName("Созданные фабриками спутники могут быть активированы")
    void factoryCreateSatellitesCanBeActivated() {
        Satellite commSatellite = communicationSatelliteFactory.createSatellite("АктивныйКомСат", 0.9);
        Satellite imgSatellite = imagingSatelliteFactory.createSatellite("АктивныйДЗЗ", 0.9);

        assertTrue(commSatellite.activate());
        assertTrue(commSatellite.getState().isActive());

        assertTrue(imgSatellite.activate());
        assertTrue(imgSatellite.getState().isActive());
    }

    @Test
    @DisplayName("Спутники с низким зарядом не могут быть активированы")
    void satellitesWithLowBatteryCannotBeActivated() {
        Satellite commSatellite = communicationSatelliteFactory.createSatellite("АктивныйКомСат", 0.1);
        Satellite imgSatellite = imagingSatelliteFactory.createSatellite("АктивныйДЗЗ", 0.1);

        assertFalse(commSatellite.activate());
        assertFalse(commSatellite.getState().isActive());

        assertFalse(imgSatellite.activate());
        assertFalse(imgSatellite.getState().isActive());
    }
}
