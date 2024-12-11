package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pairs {

    private List<Pair> pairs = new ArrayList<>();

    public Optional<Pair> findByCourseLevelMission(final Course course, final Level level, final Mission mission) {
        return pairs.stream()
                .filter(pair -> pair.getCourse() == course && pair.getLevel() == level && pair.getMission() == mission)
                .findFirst();
    }

    public void save(final Pair pair) {
        Optional<Pair> existingPair = findByCourseLevelMission(pair.getCourse(), pair.getLevel(), pair.getMission());
        if (existingPair.isPresent()) {
            pairs.remove(existingPair.get());
        }
        pairs.add(pair);
    }

    public void reset() {
        pairs.clear();
    }
}
