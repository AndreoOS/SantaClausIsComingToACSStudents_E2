package data;

import entities.Child;
import enums.AgeCategory;
import enums.Cities;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Database {
    private static Database database = null;
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChanges> annualChanges;
    private LinkedHashMap<Cities, Double> averageCityScores = new LinkedHashMap<>();

    public Database() {
        // constructor for json
    }

    public static data.Database getDatabase() {
        return database;
    }

    public static void setDatabase(final Database database) {
        Database.database = database;
    }

    /**
     * Lazy singleton implementation of database
     * @return instance of database
     */
    public static Database getInstance() {
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

    public HashMap<Cities, Double> getAverageCityScores() {
        return averageCityScores;
    }

    /**
     * This method populates the hashmap field in Database, with all the cities the children are
     * from and their corresponding average scores
     * First, the method uses an auxiliary map for storing the city and all of its scores. Using
     * this auxiliary map it calculates the average and populates the database map, then sorts it
     * by average.
     */
    public void setAverageCityScores() {
        HashMap<Cities, ArrayList<Double>> allCityScores = new HashMap<>();
        for (Child child : this.getInitialData().getChildren()) {
            if (allCityScores.get(child.getCity()) == null) {
                allCityScores.put(child.getCity(), new ArrayList<>());
            }
            allCityScores.get(child.getCity()).add(child.getAverageScore());
        }
        for (Cities city : allCityScores.keySet()) {
            Double avg = 0.0;
            for (Double averageScore : allCityScores.get(city)) {
                avg = avg + averageScore;
            }
            avg = avg / allCityScores.get(city).size();
            averageCityScores.put(city, avg);
        }
        List<Map.Entry<Cities, Double>> sorted = new ArrayList<>(averageCityScores.entrySet());
        sorted = sorted.stream().sorted((o1, o2) -> {
            if (o1.getValue().compareTo(o2.getValue()) == 0) {
                return o1.getKey().toString().compareTo(o2.getKey().toString());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        }).toList();
        averageCityScores.clear();
        for (Map.Entry<Cities, Double> entry : sorted) {
            averageCityScores.put(entry.getKey(), entry.getValue());
        }
    }
}
