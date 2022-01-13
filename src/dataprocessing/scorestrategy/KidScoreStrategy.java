package dataprocessing.scorestrategy;

import entities.Child;

public final class KidScoreStrategy implements CalculateScoreStrategy {
    private Child child;

    public KidScoreStrategy(final Child child) {
        this.child = child;
    }

    @Override
    public Double getScore() {
        Double average = 0.0;
        for (Double score : child.getNiceScoreHistory()) {
            average = average + score;
        }
        average = average / child.getNiceScoreHistory().size();
        return average;
    }
}
