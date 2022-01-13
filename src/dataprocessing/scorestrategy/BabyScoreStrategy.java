package dataprocessing.scorestrategy;

import common.Constants;
import entities.Child;

public final class BabyScoreStrategy implements CalculateScoreStrategy {

    private Child child;

    public BabyScoreStrategy(final Child child) {
        this.child = child;
    }

    @Override
    public Double getScore() {
        return Constants.MAX_NICE_SCORE;
    }
}
