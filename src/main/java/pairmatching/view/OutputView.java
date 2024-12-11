package pairmatching.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;

public class OutputView {

    private static final String LINE = "#############################################";
    private static final String COURSE_VIEW = "  과정: %s\n";
    private static final String MISSION_VIEW = "  미션:";
    private static final String LEVEL_VIEW = "    - %s: %s\n";

    private static final String DELIMITER = " | ";

    private static final String PAIR_START_VIEW = "페어 매칭 결과입니다.";
    private static final String PAIR_DELIMITER = " : ";

    private static final String RESET_VIEW = "초기화 되었습니다.";

    public static void showMission() {
        List<String> course = Arrays.stream(Course.values())
                        .map(Course::getName)
                        .collect(Collectors.toList());
        List<String> level = Arrays.stream(Level.values())
                        .map(Level::getName)
                        .collect(Collectors.toList());

        Map<Level, List<String>> groupedMission = Arrays.stream(Mission.values())
                .collect(Collectors.groupingBy(Mission::getLevel,
                        Collectors.mapping(Mission::getName, Collectors.toList())));
        List<List<String>> mission = Arrays.stream(Level.values())
                .map(eachLevel -> groupedMission.getOrDefault(eachLevel, Collections.emptyList()))
                .collect(Collectors.toList());

        System.out.println(LINE);
        String courses = String.join(DELIMITER, course);
        System.out.printf(COURSE_VIEW, courses);
        List<String> missions = makeFormattedMissions(mission);
        System.out.println(MISSION_VIEW);
        for (int idx = 0; idx < level.size(); idx++) {
            System.out.printf(LEVEL_VIEW, level.get(idx), missions.get(idx));
        }
        System.out.println(LINE);
    }

    public static void showPair(final Pair pair) {
        System.out.println(PAIR_START_VIEW);
        List<String> crewNames = pair.getCrews();
        if (crewNames.size() % 2 == 1) {
            for (int i = 0; i < crewNames.size()-3; i+=2) {
                System.out.println(String.join(PAIR_DELIMITER, crewNames.get(i), crewNames.get(i+1)));
            }
            System.out.println(String.join(PAIR_DELIMITER,
                    crewNames.get(crewNames.size()-3),
                    crewNames.get(crewNames.size()-2),
                    crewNames.get(crewNames.size()-1)));
            System.out.println();
            return;
        }
        for (int i = 0; i < crewNames.size()-1; i+=2) {
            System.out.println(String.join(PAIR_DELIMITER, crewNames.get(i), crewNames.get(i+1)));
        }
        System.out.println();
    }

    private static List<String> makeFormattedMissions(List<List<String>> mission) {
        return mission.stream()
                .map(line -> String.join(DELIMITER, line))
                .collect(Collectors.toList());
    }

    public static void noticeReset() {
        System.out.println(RESET_VIEW);
    }
}
