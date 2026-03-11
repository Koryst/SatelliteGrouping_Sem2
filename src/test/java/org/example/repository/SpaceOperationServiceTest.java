package org.example.repository;

import org.example.domains.requests.AddSatelliteRequest;
import org.example.domains.requests.MissionRequest;
import org.example.domains.requests.MissionTargetType;
import org.example.domains.satellites.CommunicationSatelliteParam;
import org.example.domains.satellites.ImagingSatelliteParam;
import org.example.domains.satellites.Satellite;
import org.example.domains.satellites.SatelliteConstellation;
import org.example.services.SpaceOperationCenterService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Интеграционные тесты фасада SpaceOperationCenterService")
public class SpaceOperationServiceTest {

    @Autowired
    private SpaceOperationCenterService spaceOperationCenterService;

    @Autowired
    private ConstellationRepository constellationRepository;

    private String uniqueName(String base) {
        return base + "_" + System.currentTimeMillis();
    }

    @Test
    @DisplayName("Добавление спутников в группировку через фасад")
    void addSatelliteTest() {
        String constellationName = uniqueName("TestConstellation");
        String commSatName = "CommSat-1";
        String imgSatName = "ImgSat-1";

        var commParam = new CommunicationSatelliteParam(commSatName, 0.9, 500.0);
        var imgParam = new ImagingSatelliteParam(imgSatName, 0.8, 2.5);
        var request = new AddSatelliteRequest(constellationName, List.of(commParam, imgParam));

        spaceOperationCenterService.addSetellite(request);

        SatelliteConstellation constellation = constellationRepository.getConstellation(constellationName);
        assertNotNull(constellation, "Группировка должна существовать");
        assertEquals(2, constellation.getSatellites().size());

        List<String> satelliteNames = constellation.getSatellites().stream()
                .map(Satellite::getName)
                .toList();
        assertTrue(satelliteNames.contains(commSatName));
        assertTrue(satelliteNames.contains(imgSatName));
    }

    @Test
    @DisplayName("Выполнение миссии для всей группировки")
    void executeConstellationMissionTest() {
        String constellationName = uniqueName("MissionConstellation");
        String satName = "MissionSat";

        var commParam = new CommunicationSatelliteParam(satName, 0.9, 500.0);
        var addRequest = new AddSatelliteRequest(constellationName, List.of(commParam));
        spaceOperationCenterService.addSetellite(addRequest);

        var missionRequest = new MissionRequest(
                MissionTargetType.CONSTELLATION,
                constellationName,
                null
        );
        spaceOperationCenterService.executeMission(missionRequest);

        SatelliteConstellation constellation = constellationRepository.getConstellation(constellationName);
        Satellite satellite = constellation.getSatellites().get(0);
        assertTrue(satellite.getState().isActive(), "Спутник должен быть активен после миссии");
    }

    @Test
    @DisplayName("Получение системной сводки")
    void getSystemOverviewTest() {
        String constellationName = uniqueName("OverviewConstellation");
        String satName = "OverviewSat";

        var commParam = new CommunicationSatelliteParam(satName, 0.9, 500.0);
        var addRequest = new AddSatelliteRequest(constellationName, List.of(commParam));
        spaceOperationCenterService.addSetellite(addRequest);

        String overview = spaceOperationCenterService.getSystemOverview();

        assertTrue(overview.contains(constellationName), "Сводка должна содержать имя группировка");
        assertTrue(overview.contains(satName), "Сводка должна содержать имя спутника");
        assertTrue(overview.contains("заряд: 80%") || overview.contains("заряд: 80"),
                "Сводка должна содержать уровень заряда (70%)");
     }
}
