package dataprocessing.strategies;

public interface CalculateScoreStrategy {

    /**
     * Function to be overwritten by strategies
     * @return average score of child
     */
    Double getScore();
}
