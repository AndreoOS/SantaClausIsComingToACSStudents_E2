package factory;

import dataprocessing.strategies.*;
import entities.Child;
import enums.AgeCategory;

public final class CalculateScoreStrategyFactory {
    private CalculateScoreStrategyFactory() {
    }

    /**
     * Method creates a strategy based on a certain child
     * @param ageCategory Category of child
     * @param child Child
     * @return a strategy which calculates the average score
     */
    public static CalculateScoreStrategy createStrategy(final AgeCategory ageCategory,
                                                        final Child child) {
        switch (ageCategory) {
            case BABY -> {
                return new BabyScoreStrategy(child);
            }
            case KID -> {
                return new KidScoreStrategy(child);
            }
            case TEEN -> {
                return new TeenScoreStrategy(child);
            }
            case YOUNG_ADULT -> {
                return new YoungAdultScoreStrategy(child);
            }
            default -> {
                return null;
            }
        }
    }
}
