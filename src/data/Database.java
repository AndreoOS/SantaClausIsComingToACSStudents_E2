package data;

import entities.Child;
import enums.AgeCategory;

import java.util.List;

public final class Database {
    private static data.Database database = null;
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChanges> annualChanges;

    public Database() {
        // constructor for json
    }

    public static data.Database getDatabase() {
        return database;
    }

    public static void setDatabase(final data.Database database) {
        data.Database.database = database;
    }

    /**
     * Lazy singleton implementation of database
     * @return instance of database
     */
    public static data.Database getInstance() {
        if (database == null) {
            database = new data.Database();
        }
        return database;
    }


    public Database(final Integer numberOfYears, final Double santaBudget,
                    final List<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.annualChanges = annualChanges;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final List<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     * The method removes children from the database if the age category is young adult
     */
    public void removeYoungAdults() {
        initialData.getChildren()
                .removeIf(child -> child.getAgeCategory().equals(AgeCategory.YOUNG_ADULT));
    }

    /**
     * Method returns the sum of all the average scores for all the children sorted by id
     * @return sum of average scores
     */
    public Double getSumOfAverage() {
        Double sum = 0.0;
        for (Child child : initialData.sortChildrenById()) {
            sum = sum + child.getAverageScore();
        }
        return sum;
    }
}
