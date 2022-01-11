package actions;

import data.Database;
import data.GiftList;
import entities.Child;
import entities.Gift;
import enums.Category;
import enums.ElvesType;

public class ElfModifier {
    Database database;
    GiftList giftList;

    public ElfModifier(Database database, GiftList giftList) {
        this.database = database;
        this.giftList = giftList;
    }

    public void applyBlackPinkElfModifier() {
        for (Child child : database.getInitialData().getChildren()) {
            if (child.getElf().equals(ElvesType.BLACK)) {
                Double newBudget = child.getAssignedBudget();
                newBudget -= ((newBudget * 30) / 100);
                child.setAssignedBudget(newBudget);
            } else if (child.getElf().equals(ElvesType.PINK)) {
                Double newBudget = child.getAssignedBudget();
                newBudget += ((newBudget * 30) / 100);
                child.setAssignedBudget(newBudget);
            }
        }
    }

    public void applyYellowElfModifier() {
        for (Child child :database.getInitialData().getChildren()) {
            if (child.getReceivedGifts().isEmpty()) {
                Category favoriteCateg = child.getGiftsPreferences().get(0);
                Gift assignedGift = null;

            }
        }


    }
}
