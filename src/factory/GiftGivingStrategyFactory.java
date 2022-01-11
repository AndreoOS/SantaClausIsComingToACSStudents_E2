package factory;

import data.Database;
import data.GiftList;
import dataprocessing.strategies.GiftGivingStrategy;
import dataprocessing.strategies.IdGiftStrategy;
import dataprocessing.strategies.NiceScoreGiftStrategy;

public class GiftGivingStrategyFactory {
    public GiftGivingStrategyFactory() {
    }

    public static GiftGivingStrategy createStrategy(final String strategy, final Database database,
                                                    final GiftList giftList) {
        switch(strategy) {
            case ("id") -> {
                return new IdGiftStrategy(database, giftList);
            }
            case ("niceScore") -> {
                return new NiceScoreGiftStrategy(database, giftList);
            }
            case ("niceScoreCity") -> {
                return null;
            }
            default -> {
                return null;
            }
        }

    }
}
