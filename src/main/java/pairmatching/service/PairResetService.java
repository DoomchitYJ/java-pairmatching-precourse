package pairmatching.service;

import pairmatching.domain.Pairs;
import pairmatching.view.OutputView;

public class PairResetService {

    private Pairs pairs;

    public PairResetService(Pairs pairs) {
        this.pairs = pairs;
    }

    public Pairs operate() {
        pairs.reset();
        OutputView.noticeReset();
        return pairs;
    }
}
