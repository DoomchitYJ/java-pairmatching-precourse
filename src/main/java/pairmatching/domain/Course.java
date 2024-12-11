package pairmatching.domain;

import static pairmatching.config.Config.BACKEND_CREW_FILE;
import static pairmatching.config.Config.FRONTEND_CREW_FILE;
import static pairmatching.exception.ExceptionMessage.NO_COURSE;

import pairmatching.exception.PairmatchingException;

public enum Course {

    BACKEND("백엔드", BACKEND_CREW_FILE),
    FRONTEND("프론트엔드", FRONTEND_CREW_FILE);

    private final String name;
    private final String filePath;

    Course(final String name, final String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public static Course fromName(String name) {
        for (Course course : values()) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        throw new PairmatchingException(NO_COURSE);
    }
}
