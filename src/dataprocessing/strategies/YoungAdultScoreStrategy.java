package dataprocessing.strategies;

import entities.Child;

public final class YoungAdultScoreStrategy implements CalculateScoreStrategy {
    private Child child;

    public YoungAdultScoreStrategy(final Child child) {
        this.child = child;
    }

    @Override
    public Double getScore() {
        return 0.0;
    }
}
