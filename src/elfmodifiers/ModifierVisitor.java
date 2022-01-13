package elfmodifiers;

import common.Constants;
import entities.Child;
import entities.Gift;
import enums.Category;
import enums.ElvesType;

public final class ModifierVisitor implements Visitor {

    /**
     * Method implements behaviour if BlackElf is visited
     * BlackElf cuts the budget by 30%
     * @param elf elf to be visited
     */
    @Override
    public void visit(final BlackElf elf) {
        for (Child child : elf.getDatabase().getInitialData().getChildren()) {
            if (child.getElf().equals(ElvesType.BLACK)) {
                Double newBudget = child.getAssignedBudget();
                newBudget -= ((newBudget * Constants.PERCENTAGE) / Constants.MAX_PERCENT);
                child.setAssignedBudget(newBudget);
            }
        }
    }

    /**
     * Method implements behaviour if PinkElf is visited
     * PinkElf raises the budget by 30%
     * @param elf elf to be visited
     */
    @Override
    public void visit(final PinkElf elf) {
        for (Child child : elf.getDatabase().getInitialData().getChildren()) {
            if (child.getElf().equals(ElvesType.PINK)) {
                Double newBudget = child.getAssignedBudget();
                newBudget += ((newBudget * Constants.PERCENTAGE) / Constants.MAX_PERCENT);
                child.setAssignedBudget(newBudget);
            }
        }

    }

    /**
     * Method implements behaviour if YellowElf is visited
     * YellowElf gives a gift to a giftless child
     * @param elf elf to be visited
     */
    @Override
    public void visit(final YellowElf elf) {
        for (Child child : elf.getDatabase().getInitialData().getChildren()) {
            if (child.getElf().equals(ElvesType.YELLOW)) {
                if (child.getReceivedGifts().isEmpty()) {
                    Category favoriteCateg = child.getGiftsPreferences().get(0);
                    Gift assignedGift = null;
                    if (elf.getGiftList().getSpecifiedList(favoriteCateg) != null) {
                        for (Gift foundGift : elf.getGiftList().getSpecifiedList(favoriteCateg)) {
                            if (assignedGift != null) {
                                if (assignedGift.getPrice()
                                        .compareTo(foundGift.getPrice()) > 0) {
                                    assignedGift = foundGift;
                                }
                            } else {
                                assignedGift = foundGift;
                            }
                        }
                    }
                    if (assignedGift != null) {
                        if (assignedGift.getQuantity() > 0) {
                            assignedGift.setQuantity(assignedGift.getQuantity() - 1);
                            child.getReceivedGifts().add(assignedGift);
                        }
                    }
                }
            }
        }
    }
}
