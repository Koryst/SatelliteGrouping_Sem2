package org.example;

import org.example.domains.Satellite;
import org.example.factory.SatelliteFactory;
import org.example.factory.impl.CommunicationSatelliteFactory;
import org.example.factory.impl.ImagingSatelliteFactory;
import org.example.repository.ConstellationRepository;
import org.example.services.SpaceOperationCenterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        ConstellationRepository repository = ctx.getBean(ConstellationRepository.class);
        SpaceOperationCenterService operationCenter = ctx.getBean(SpaceOperationCenterService.class);
        SatelliteFactory communicationFactory = ctx.getBean(CommunicationSatelliteFactory.class);
        SatelliteFactory imagingFactory = ctx.getBean(ImagingSatelliteFactory.class);

        System.out.println("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");
        System.out.println("=".repeat(60));

        System.out.println("СОЗДАНИЕ СПЕЦИАЛИЗИРОВАННЫХ СПУТНИКОВ");
        System.out.println("=".repeat(45));

        Satellite commSat1 = communicationFactory.createSatellite("Связь-1", 0.85, 500);
        Satellite commSat2 = communicationFactory.createSatellite("Связь-2", 0.75, 1000);

        Satellite imgSat1 = imagingFactory.createSatellite("ДЗЗ-1", 0.92, 2.5);
        Satellite imgSat2 = imagingFactory.createSatellite("ДЗЗ-2", 0.45, 1.0);
        Satellite imgSat3 = imagingFactory.createSatellite("ДЗЗ-3", 0.15, 0.5);
        System.out.println("-".repeat(45));

        operationCenter.createAndSaveConstellation("Орбита-1");
        operationCenter.createAndSaveConstellation("Орбита-2");
        System.out.println("=".repeat(45));

        System.out.println("ФОРМИРОВАНИЕ ГРУППИРОВКИ:");
        System.out.println("-".repeat(35));
        operationCenter.addSatelliteToConstellation("Орбита-1", commSat1);
        operationCenter.addSatelliteToConstellation("Орбита-1", imgSat1);
        operationCenter.addSatelliteToConstellation("Орбита-1", imgSat2);

        operationCenter.addSatelliteToConstellation("Орбита-2", commSat2);
        operationCenter.addSatelliteToConstellation("Орбита-2", imgSat3);
        System.out.println("-".repeat(35));

        operationCenter.showConstellationStatus("Орбита-1");
        operationCenter.showConstellationStatus("Орбита-2");
        System.out.println("-".repeat(35));

        operationCenter.activateAllSatellites("Орбита-1");
        operationCenter.executeConstellationMission("Орбита-1");
        operationCenter.showConstellationStatus("Орбита-1");

        System.out.println(repository.getConstellations().toString());
    }
}