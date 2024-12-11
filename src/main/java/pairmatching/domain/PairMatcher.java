package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class PairMatcher {

    public static List<String> matchPair(List<String> names) {
        return Randoms.shuffle(names);
    }
}
