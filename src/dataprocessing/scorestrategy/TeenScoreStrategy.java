package dataprocessing.scorestrategy;

import entities.Child;

public final class TeenScoreStrategy implements CalculateScoreStrategy {
    private Child child;

    public TeenScoreStrategy(final Child child) {
        this.child = child;
    }

    @Override
    public Double getScore() {
        Double average = 0.0;
        Integer weight = 0;
        for (int i = 0; i < child.getNiceScoreHistory().size(); i++) {
            average = average + (i + 1) * child.getNiceScoreHistory().get(i);
            weight = weight + (i + 1);
        }
        average = average / weight;
        return average;
    }
}
