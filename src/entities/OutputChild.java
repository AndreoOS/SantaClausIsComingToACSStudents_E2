package entities;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;

public final class OutputChild {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private List<Category> giftsPreferences;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<OutputGift> receivedGifts;

    public OutputChild(final Child child) {
        id = child.getId();
        lastName = child.getLastName();
        firstName = child.getFirstName();
        city = child.getCity();
        age = child.getAge();
        giftsPreferences = cloneGiftPreferences(child.getGiftsPreferences()); // clone
        averageScore = child.getAverageScore();
        niceScoreHistory = cloneNiceScoreHistory(child.getNiceScoreHistory()); // clone
        assignedBudget = child.getAssignedBudget();
        receivedGifts = cloneReceivedGifts(child.getReceivedGifts()); // clone
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<OutputGift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final ArrayList<OutputGift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * Method clones the list given as parameter, so the list doesn't change after simulating
     * years
     * @param theReceivedGifts list of gifts received that year
     * @return a list of output gifts
     */
    private List<OutputGift> cloneReceivedGifts(final List<Gift> theReceivedGifts) {
        List<OutputGift> clone = new ArrayList<>();
        for (Gift gift : theReceivedGifts) {
            clone.add(new OutputGift(gift));
        }
        return clone;
    }

    /**
     * Method clones the gift preferences so that they cannot be changed externally
     * @param giftPreferences preferences to be cloned
     * @return cloned list
     */
    private List<Category> cloneGiftPreferences(final List<Category> giftPreferences) {
        return new ArrayList<>(giftPreferences);
    }

    /**
     * Method clones the nice score history list so that they cannot be changed externally
     * @param theNiceScoreHistory scores to be cloned
     * @return cloned list
     */
    private List<Double> cloneNiceScoreHistory(final List<Double> theNiceScoreHistory) {
        return new ArrayList<>(theNiceScoreHistory);
    }
}
