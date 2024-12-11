package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class Pair {

    private final Course course;
    private final Level level;
    private final Mission mission;
    private static List<String> crews;

    public Pair(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;

        crews = PairMatcher.matchPair(CrewNameList.getNames(course));
    }

    public Course getCourse() {
        return course;
    }
    public Level getLevel() {
        return level;
    }
    public Mission getMission() {
        return mission;
    }
    public List<String> getCrews() {
        return crews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return course == pair.course && level == pair.level && mission == pair.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}
