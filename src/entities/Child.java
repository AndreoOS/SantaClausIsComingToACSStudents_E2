package entities;

import common.Constants;
import enums.AgeCategory;
import enums.Category;
import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;
import java.util.List;

public final class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private Double niceScore;
    private List<Category> giftsPreferences;
    private Integer niceScoreBonus;
    private ElvesType elf;
    private AgeCategory ageCategory;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<Gift> receivedGifts;


    public Child() {
        // constructor for json
        this.niceScoreHistory = new ArrayList<>();
        this.receivedGifts = new ArrayList<>();
    }

    public Child(final Integer id, final String firstName, final String lastName,
                 final Integer age, final Cities city,
                 final Double niceScore,  final List<Category> giftsPreferences) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    /**
     * Method sets the age category of child
     */
    public void setAgeCategory() {
        if (age < Constants.MAX_BABY_AGE) {
            this.ageCategory = AgeCategory.BABY;
        } else if (age < Constants.MAX_KID_AGE) {
            this.ageCategory = AgeCategory.KID;
        } else if (age <= Constants.MAX_TEEN_AGE) {
            this.ageCategory = AgeCategory.TEEN;
        } else {
            this.ageCategory = AgeCategory.YOUNG_ADULT;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
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

    public Integer getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(Integer niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setElf(ElvesType elf) {
        this.elf = elf;
    }
}
