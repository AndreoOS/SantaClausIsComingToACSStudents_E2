package factory;

import data.Database;
import data.GiftList;
import dataprocessing.giftstrategy.GiftGivingStrategy;
import dataprocessing.giftstrategy.IdGiftStrategy;
import dataprocessing.giftstrategy.NiceScoreCityGiftStrategy;
import dataprocessing.giftstrategy.NiceScoreGiftStrategy;

public final class GiftGivingStrategyFactory {
    private GiftGivingStrategyFactory() {
    }

    /**
     * Method creates a strategy based on the strategy string.
     * @param strategy type of strategy to be created
     * @param database database
     * @param giftList list of gifts
     * @return a strategy that distributes gifts
     */
    public static GiftGivingStrategy createStrategy(final String strategy, final Database database,
                                                    final GiftList giftList) {
        switch (strategy) {
            case ("id") -> {
                return new IdGiftStrategy(database, giftList);
            }
            case ("niceScore") -> {
                return new NiceScoreGiftStrategy(database, giftList);
            }
            case ("niceScoreCity") -> {
                return new NiceScoreCityGiftStrategy(database, giftList);
            }
            default -> {
                return null;
            }
        }

    }
}
