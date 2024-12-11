package pairmatching.controller;

import static pairmatching.constant.Constant.MAX_TRY;
import static pairmatching.exception.ExceptionMessage.MAX_TRY_ERROR;
import static pairmatching.exception.ExceptionMessage.NO_FUNCTION_ERROR;
import static pairmatching.view.ErrorPrinter.printError;

import pairmatching.domain.Pairs;
import pairmatching.exception.PairmatchingException;
import pairmatching.service.PairCheckService;
import pairmatching.service.PairMatchingService;
import pairmatching.service.PairResetService;
import pairmatching.view.InputView;

public class Controller {

    private static Pairs pairs = new Pairs();

    private static final String PAIR_MATCHING_FUNC = "1";
    private static final String PAIR_CHECK_FUNC = "2";
    private static final String PAIR_RESET_FUNC = "3";
    private static final String PAIR_QUIT_FUNC = "Q";

    public void run() {
        boolean running = true;
        while (running) {
            String functionInput = readFunction();
            if (functionInput.equals(PAIR_MATCHING_FUNC)) {
                PairMatchingService pairMatchingService = new PairMatchingService(pairs);
                pairMatchingService.operate();
                continue;
            }
            if (functionInput.equals(PAIR_CHECK_FUNC)) {
                PairCheckService pairCheckService = new PairCheckService(pairs);
                try {
                    pairCheckService.operate();
                    continue;
                } catch (PairmatchingException e) {
                    printError(e.getMessage());
                }
            }
            if (functionInput.equals(PAIR_RESET_FUNC)) {
                PairResetService pairResetService = new PairResetService(pairs);
                pairs = pairResetService.operate();
                continue;
            }
            if (functionInput.equals(PAIR_QUIT_FUNC)) {
                running = false;
            }
        }
    }

    private String readFunction() {
        for (int i = 1; i <= MAX_TRY; i++) {
            try {
                String input = InputView.readFunction();
                if (isValidInput(input)) {
                    return input;
                }
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new PairmatchingException(MAX_TRY_ERROR);
    }

    private boolean isValidInput(String input) {
        if (input.equals(PAIR_MATCHING_FUNC)
                || input.equals(PAIR_CHECK_FUNC)
                || input.equals(PAIR_RESET_FUNC)
                || input.equals(PAIR_QUIT_FUNC)) {
            return true;
        }
        throw new PairmatchingException(NO_FUNCTION_ERROR);
    }
}
