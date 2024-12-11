package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.NO_LEVEL;

import pairmatching.exception.PairmatchingException;

public enum Level {

    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Level fromName(String name) {
        for (Level level : values()) {
            if (level.getName().equals(name)) {
                return level;
            }
        }
        throw new PairmatchingException(NO_LEVEL);
    }
}
