package data;

import entities.Child;
import entities.ChildUpdate;
import entities.Gift;
import enums.Category;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AnnualChanges {
    private Double newSantaBudget;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildUpdate> childrenUpdates;
    private String strategy;

    public AnnualChanges() {
        // constructor for json
    }

    public AnnualChanges(final Double newSantaBudget, final List<Gift> newGifts,
                         final List<Child> newChildren, final List<ChildUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     * Method adds the new children from the annual change to the database, and sets their nice
     * score history.
     * @param database
     */
    public void addNewChildren(final Database database) {
        for (Child newChild : newChildren) {
            newChild.getNiceScoreHistory().add(newChild.getNiceScore());
            database.getInitialData().getChildren().add(newChild);
        }
    }

    /**
     * For every child update the method finds the child with the corresponding id then applies the
     * changes: sets the new nice score then updates nice score history, updates gift preferences.
     * @param database
     */
    public void updateChildren(final Database database) {
        for (ChildUpdate childUpdate : childrenUpdates) {
            Child foundChild = database.getInitialData().getChildWithId(childUpdate.getId());
            if (foundChild != null) {
                if (childUpdate.getNiceScore() != null) {
                    foundChild.setNiceScore(childUpdate.getNiceScore());
                    foundChild.getNiceScoreHistory().add(childUpdate.getNiceScore());
                }
                if (childUpdate.getGiftsPreferences().size() != 0) {
                    for (Category newCategory : childUpdate.getGiftsPreferences()) {
                        foundChild.getGiftsPreferences().remove(newCategory);
                    }
                    List<Category> newGiftPreferences = Stream
                            .concat(childUpdate.getGiftsPreferences().stream(),
                            foundChild.getGiftsPreferences().stream())
                            .collect(Collectors.toList());
                    newGiftPreferences = newGiftPreferences.stream().distinct()
                            .collect(Collectors.toList());
                    foundChild.setGiftsPreferences(newGiftPreferences);

                }
                if (childUpdate.getElf() != null) {
                    foundChild.setElf(childUpdate.getElf());
                }
            }
        }
    }

    /**
     * Wrapper method for setting the new Santa budget
     * @param database
     */
    public void updateBudget(final Database database) {
        database.setSantaBudget(newSantaBudget);
    }

    public void addNewGifts(final Database database, final GiftList giftList) {
        for (Gift gift : newGifts) {
            database.getInitialData().getSantaGiftsList().add(gift);
            giftList.addToGiftList(gift);
        }
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }


}
