package pairmatching.service;

import static pairmatching.constant.Constant.MAX_TRY;
import static pairmatching.exception.ExceptionMessage.MAX_TRY_ERROR;
import static pairmatching.exception.ExceptionMessage.NO_MATCHING_ERROR;
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

public class PairCheckService {

    private static final String DELIMITER = ",";
    private static final int COURSE_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int MISSION_INDEX = 2;

    private Pairs pairs;

    public PairCheckService(Pairs pairs) {
        this.pairs = pairs;
    }

    public void operate() {
        OutputView.showMission();
        showResult();
    }

    private void showResult() {
        Optional<Pair> pair = findPair();
        if (pair.isPresent()) {
            OutputView.showPair(pair.get());
            return;
        }
        throw new PairmatchingException(NO_MATCHING_ERROR);
    }

    private Optional<Pair> findPair() {
        for (int i = 1; i <= MAX_TRY; i++) {
            try {
                String[] input = InputView.readMission().split(DELIMITER);
                Course course = Course.fromName(input[COURSE_INDEX].trim());
                Level level = Level.fromName(input[LEVEL_INDEX].trim());
                Mission mission = Mission.fromName(input[MISSION_INDEX].trim());
                return pairs.findByCourseLevelMission(course, level, mission);
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new PairmatchingException(MAX_TRY_ERROR);
    }
}
