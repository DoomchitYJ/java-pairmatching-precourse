package pairmatching.domain;

import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;
import static pairmatching.domain.Level.LEVEL4;
import static pairmatching.exception.ExceptionMessage.NO_MISSION;

import pairmatching.exception.PairmatchingException;

public enum Mission {

    RACINGCAR("자동차경주", LEVEL1),
    LOTTO("로또", LEVEL1),
    BASEBALL("숫자야구게임", LEVEL1),
    CART("장바구니", LEVEL2),
    PAYMENT("결제", LEVEL2),
    SUBWAY("지하철노선도", LEVEL2),
    IMPROVEMENT("성능개선", LEVEL4),
    DEPLOY("배포", LEVEL4);

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }
    public Level getLevel() {
        return level;
    }

    public static Mission fromName(String name) {
        for (Mission mission : values()) {
            if (mission.getName().equals(name)) {
                return mission;
            }
        }
        throw new PairmatchingException(NO_MISSION);
    }
}
