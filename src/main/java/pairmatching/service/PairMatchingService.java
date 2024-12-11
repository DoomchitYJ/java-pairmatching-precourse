package pairmatching.service;

import static pairmatching.constant.Constant.MAX_TRY;
import static pairmatching.exception.ExceptionMessage.LEVEL_MISSION_ERROR;
import static pairmatching.exception.ExceptionMessage.MAX_TRY_ERROR;
import static pairmatching.exception.ExceptionMessage.REPLY_ERROR;
import static pairmatching.view.ErrorPrinter.printError;

import java.util.Optional;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;
import pairmatching.exception.PairmatchingException;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingService {

    private static final String DELIMITER = ",";
    private static final int COURSE_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int MISSION_INDEX = 2;

    private static final String REPLY_YES = "네";
    private static final String REPLY_NO = "아니오";

    private Pairs pairs;

    public PairMatchingService(Pairs pairs) {
        this.pairs = pairs;
    }

    public void operate() {
        OutputView.showMission();
        Pair pair = readCourseLevelMission();
        OutputView.showPair(pair);
    }

    private Pair readCourseLevelMission() {
        for (int i = 1; i <= MAX_TRY; i++) {
            try {
                String[] input = InputView.readMission().split(DELIMITER);
                Course course = Course.fromName(input[COURSE_INDEX].trim());
                Level level = Level.fromName(input[LEVEL_INDEX].trim());
                Mission mission = Mission.fromName(input[MISSION_INDEX].trim());
                checkLevelMission(level, mission);
                Optional<Pair> pair = pairs.findByCourseLevelMission(course, level, mission);
                if (pair.isPresent()) {
                    String reply = readReply();
                    if (!reply.equals(REPLY_YES)) {
                        return readCourseLevelMission();
                    }
                }
                Pair newPair = new Pair(course, level, mission);
                pairs.save(newPair);
                return newPair;
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new PairmatchingException(MAX_TRY_ERROR);
    }

    private void checkLevelMission(Level level, Mission mission) {
        if (mission.getLevel() != level) {
            throw new PairmatchingException(LEVEL_MISSION_ERROR);
        }
    }
    private String readReply() {
        for (int i = 1; i <= MAX_TRY; i++) {
            try {
                String reply = InputView.readReply();
                validateReply(reply);
                return reply;
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new PairmatchingException(MAX_TRY_ERROR);
    }

    private void validateReply(String reply) {
        if (!reply.equals(REPLY_YES) && !reply.equals(REPLY_NO)) {
            throw new PairmatchingException(REPLY_ERROR);
        }
    }
}
