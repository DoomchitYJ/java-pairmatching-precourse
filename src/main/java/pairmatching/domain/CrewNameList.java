package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.repository.CrewNameRepository;

public class CrewNameList {

    private static Map<Course, List<String>> crewNames;

    public static List<String> getNames(Course course) {
        if (crewNames == null) {
            initializeCrewNames();
        }
        return crewNames.get(course);
    }

    private static void initializeCrewNames() {
        crewNames = new HashMap<>();
        CrewNameRepository crewNameRepository = new CrewNameRepository();
        for (Course course : Course.values()) {
            crewNames.put(course, crewNameRepository.loadCrew(course));
        }
    }
}
