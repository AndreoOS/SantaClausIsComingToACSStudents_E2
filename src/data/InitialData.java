package data;

import entities.Child;
import entities.Gift;
import enums.Cities;

import java.util.ArrayList;
import java.util.Comparator;
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
        return children.stream().sorted(Comparator.comparing(Child::getId)).toList();
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

    /**
     * Method takes the all the children and sorts them based on their average score (if the
     * average scores are equal, they are sorted by the id)
     * @return a sorted list of children
     */
    public List<Child> sortChildrenByNiceScoreAverage() {
        return children.stream().sorted(((o1, o2) -> {
            if (o1.getAverageScore().compareTo(o2.getAverageScore()) == 0) {
                return o1.getId().compareTo(o2.getId());
            } else {
                return o2.getAverageScore().compareTo(o1.getAverageScore());
            }
        })).toList();
    }

    /**
     * Method returns a list of all the children from a city, sorted by their id
     * @param city the city that the children live in
     * @return sorted children list
     */
    public List<Child> getChildrenInCity(final Cities city) {
        List<Child> result = new ArrayList<>();
        for (Child child : children) {
            if (child.getCity().equals(city)) {
                result.add(child);
            }
        }
        result.stream().sorted(Comparator.comparing(Child::getId)).toList();

        return result;
    }

}
