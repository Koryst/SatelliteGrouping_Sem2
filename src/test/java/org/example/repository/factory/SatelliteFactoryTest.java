package org.example.repository.factory;

import org.example.domains.*;
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

    @BeforeEach
    void setUp() {
        communicationSatelliteFactory = new CommunicationSatelliteFactory();
        imagingSatelliteFactory = new ImagingSatelliteFactory();
    }

    @Test
    @DisplayName("Фабрика спутников связи создает спутник с кастомными параметрами")
    void communicationFactoryCreatesSatelliteWithCustomParameters() {
        String name = "КомСат-2";
        double batteryLevel = 0.9;
        double bandwidth = 500.0;

        Satellite satellite = communicationSatelliteFactory.createSatellite(
                new CommunicationSatelliteParam(name, batteryLevel, bandwidth)
        );

        assertNotNull(satellite);
        assertInstanceOf(CommunicationSatellite.class, satellite);
        assertEquals(name, satellite.getName());

        CommunicationSatellite communicationSatellite = (CommunicationSatellite) satellite;
        assertEquals(bandwidth, communicationSatellite.getBandwidth(), 0.001);
    }

    @Test
    @DisplayName("Фабрика спутников ДЗЗ создает спутник с кастомными параметрами")
    void imagingFactoryCreatesSatelliteWithCustomParameters() {
        String name = "ДЗЗ-Сат-2";
        double batteryLevel = 0.85;
        double resolution = 2.5;

        Satellite satellite = imagingSatelliteFactory.createSatellite(
                new ImagingSatelliteParam(name, batteryLevel, resolution)
        );

        assertNotNull(satellite);
        assertInstanceOf(ImagingSatellite.class, satellite);
        assertEquals(name, satellite.getName());

        ImagingSatellite communicationSatellite = (ImagingSatellite) satellite;
        assertEquals(resolution, communicationSatellite.getResolution(), 0.001);
    }

    @Test
    @DisplayName("Фабрики реализуют общий интерфейс SatelliteFactory")
    void factoriesImplementCommonInterface() {
        String name = "ДЗЗ";
        double batteryLevel = 0.9;
        double bandwidth = 500.0;
        double resolution = 2.5;

        Satellite commSatellite = communicationSatelliteFactory.createSatellite(
                new CommunicationSatelliteParam(name, batteryLevel, bandwidth)
        );

        Satellite imgSatellite = imagingSatelliteFactory.createSatellite(
                new ImagingSatelliteParam(name, batteryLevel, resolution)
        );

        assertNotNull(commSatellite);
        assertNotNull(imgSatellite);
        assertInstanceOf(Satellite.class, commSatellite);
        assertInstanceOf(Satellite.class, imgSatellite);
    }
}
