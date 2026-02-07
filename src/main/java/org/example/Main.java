package org.example;

import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.setErr(new PrintStream(System.err, true, "UTF-8"));

        System.out.println("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");
        System.out.println("=".repeat(60));

        System.out.println("СОЗДАНИЕ СПЕЦИАЛИЗИРОВАННЫХ СПУТНИКОВ");
        System.out.println("=".repeat(45));

        CommunicationSatellite commSata1 = new CommunicationSatellite("Связь-1", 0.85, 500);
        CommunicationSatellite commSata2 = new CommunicationSatellite("Связь-2", 0.75, 1000);

        ImagingSatellite imgSat1 = new ImagingSatellite("ДЗЗ-1", 0.92, 2.5);
        ImagingSatellite imgSat2 = new ImagingSatellite("ДЗЗ-2", 0.45, 1.0);
        ImagingSatellite imgSat3 = new ImagingSatellite("ДЗЗ-3", 0.15, 0.5);
        System.out.println("=".repeat(45));

        SatelliteConstellation constellation = new SatelliteConstellation("RU Basic");
        System.out.println("=".repeat(45));

        System.out.println("ФОРМИРОВАНИЕ ГРУППИРОВКИ:");
        System.out.println("=".repeat(35));
        constellation.addSatellite(commSata1);
        constellation.addSatellite(commSata2);
        constellation.addSatellite(imgSat1);
        constellation.addSatellite(imgSat2);
        constellation.addSatellite(imgSat3);
        System.out.println("=".repeat(35));

        System.out.println(constellation.getSatellites());
        System.out.println("=".repeat(35));

        System.out.println("АКТИВАЦИЯ СПУТНИКОВ:");
        System.out.println("=".repeat(25));
        commSata1.activate();
        commSata2.activate();
        imgSat1.activate();
        imgSat2.activate();
        imgSat3.activate();

        constellation.executeAllMissions();;

        System.out.println(constellation.getSatellites());
    }
}