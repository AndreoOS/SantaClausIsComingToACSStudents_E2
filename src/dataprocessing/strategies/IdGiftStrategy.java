package dataprocessing.strategies;

import data.Database;
import data.GiftList;
import entities.Child;
import entities.Gift;
import enums.Category;

public class IdGiftStrategy implements GiftGivingStrategy{
    private Database database;
    private GiftList giftList;


    public IdGiftStrategy(Database database, final GiftList giftList) {
        this.database = database;
        this.giftList = giftList;
    }

    @Override
    public void distributeGifts() {
        Gift assignedGift;
        for (Child child : database.getInitialData().sortChildrenById()) {
            database.getInitialData().removeNoQuantity();
            giftList.removeNoQuantity();
            Double budget = child.getAssignedBudget();
            for (Category giftCategory : child.getGiftsPreferences()) {
                assignedGift = null;
                if (giftList.getSpecifiedList(giftCategory) != null) {
                    for (Gift foundGift : giftList.getSpecifiedList(giftCategory)) {
                        if (assignedGift != null) {
                            if (assignedGift.getPrice().compareTo(foundGift.getPrice()) > 0) {
                                assignedGift = foundGift;
                            }
                        } else {
                            assignedGift = foundGift;
                        }
                    }
                }
                if (assignedGift != null) {
                    if (Double.compare(budget, assignedGift.getPrice()) > 0
                            && !child.getReceivedGifts().contains(assignedGift) && assignedGift.getQuantity() > 0) {
                        assignedGift.setQuantity(assignedGift.getQuantity() - 1);
                        child.getReceivedGifts().add(assignedGift);
                        budget = budget - assignedGift.getPrice();
                    }
                }
            }
        }
    }
}
