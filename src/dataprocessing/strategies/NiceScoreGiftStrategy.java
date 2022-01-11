package dataprocessing.strategies;

import data.Database;
import data.GiftList;
import entities.Child;
import entities.Gift;
import enums.Category;

public class NiceScoreGiftStrategy implements GiftGivingStrategy {
    private final Database database;
    private final GiftList giftList;


    public NiceScoreGiftStrategy(Database database, final GiftList giftList) {
        this.database = database;
        this.giftList = giftList;
    }

    @Override
    public void distributeGifts() {
        Gift assignedGift;
        for (Child child : database.getInitialData().sortChildrenByNiceScoreAverage()) {
            Double budget = child.getAssignedBudget();
            for (Category giftCategory : child.getGiftsPreferences()) {
                assignedGift = null;
                if (giftList.getSpecifiedList(giftCategory) != null) {
                    for (Gift foundGift : giftList.getSpecifiedList(giftCategory)) {
                        if (foundGift.getQuantity() > 0) {
                            if (assignedGift != null) {
                                if (assignedGift.getPrice().compareTo(foundGift.getPrice()) > 0) {
                                    assignedGift = foundGift;
                                }
                            } else {
                                assignedGift = foundGift;
                            }
                        }
                    }
                }
                if (assignedGift != null) {
                    if (Double.compare(budget, assignedGift.getPrice()) > 0
                            && !child.getReceivedGifts().contains(assignedGift)) {
                        assignedGift.setQuantity(assignedGift.getQuantity() - 1);
                        child.getReceivedGifts().add(assignedGift);
                        budget = budget - assignedGift.getPrice();
                    }
                }
            }
        }
    }
}
