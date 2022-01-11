package data;

import entities.Child;
import entities.Gift;

import java.util.ArrayList;
import java.util.List;

public final class InitialData {
    private List<Child> children;
    private List<Gift> santaGiftsList;

    public InitialData() {
        // constructor for json
        children = new ArrayList<>();
        santaGiftsList = new ArrayList<>();
    }

    public InitialData(final List<Child> children, final List<Gift> santaGiftsList) {
        this.children = children;
        this.santaGiftsList = santaGiftsList;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    /**
     * Method sorts the list of children based on id
     * @return sorted list
     */
    public List<Child> sortChildrenById() {
        return children.stream().sorted((o1, o2) -> {
            if (o1.getId().compareTo(o2.getId()) > 0) {
                return o1.getId().compareTo(o2.getId());
            } else {
                return o2.getId().compareTo(o1.getId());
            }
        }).toList();
    }

    /**
     * Returns child with specific id
     * @param id integer
     * @return wanted child
     */
    public Child getChildWithId(final Integer id) {
        for (Child child : children) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    public List<Child> sortChildrenByNiceScoreAverage() {
        List<Child> sortedList = children.stream().sorted(((o1, o2) -> {
            if (o1.getAverageScore().compareTo(o2.getAverageScore()) == 0) {
                return o1.getId().compareTo(o2.getId());
            } else {
                return o2.getAverageScore().compareTo(o1.getAverageScore());
            }
        })).toList();
        return sortedList;
    }

    public void removeNoQuantity() {
        santaGiftsList.removeIf(gift -> gift.getQuantity() == 0);
    }
}
