package actions;

import data.AnnualChanges;
import data.Database;
import data.GiftList;
import data.OutputDatabase;
import dataprocessing.strategies.CalculateScoreStrategy;
import dataprocessing.strategies.GiftGivingStrategy;
import entities.*;
import enums.Category;
import enums.ElvesType;
import factory.CalculateScoreStrategyFactory;
import factory.GiftGivingStrategyFactory;

import java.util.Map;

public final class Simulation {
    private Database database;
    private Double budgetUnit;
    private GiftList giftList;

    public Simulation(final Database database, final GiftList giftList) {
        this.database = database;
        this.giftList = giftList;
    }

    /**
     * Wrapper method that calls the methods for simulating round 0 and the subsequent
     * rounds
     * @param odb Database to store the results
     */
    public void simulateAll(final OutputDatabase odb) {
        initialRound(odb);
        simulateYears(odb);
    }

    /**
     * Method that simulates round 0. Sets the list for all the nice scores, age categories,
     * removes all the young adults from the database then sets the assigned budged for every child
     * in the database. After, the gifts are distributed. All of the children from the database are
     * then transformed into output children (for output purposes) then put into a list. That list
     * is then put into another list (output database.annualChildren). The received gifts are
     * then removed from every child's list
     * @param odb Database that stores the results
     */
    private void initialRound(final OutputDatabase odb) {
        setNiceScoreHistory();
        setAgeCategoriesAndCalculateAverage();
        database.removeYoungAdults();
        setAssignedBudgets();
        ElfModifier modifier = new ElfModifier(database);
        modifier.applyBlackPinkElfModifier();
        giveGifts("id");
        Children giftedChildren = new Children();
        for (Child child : database.getInitialData().getChildren()) {
            OutputChild outputChild = new OutputChild(child);
            for (Gift receivedGift : child.getReceivedGifts()) {
                OutputGift outputGift = new OutputGift(receivedGift);
                outputChild.getReceivedGifts().add(outputGift);
            }
            giftedChildren.getChildren().add(outputChild);
        }
        odb.getAnnualChildren().add(giftedChildren);
        removeGifts();
    }

    /**
     * Firstly, the method increments the age of every child in the database then sets the age
     * categories once again. After that, method goes through all the annual changes: adds new
     * children, modifies the current ones, and updates the children. After that, method is similar
     * to initialRound method.
     * @param odb Database that stores the results
     */
    private void simulateYears(final OutputDatabase odb) {
        for (int i = 1; i <= database.getNumberOfYears(); i++) {
            for (Child child : database.getInitialData().getChildren()) {
                child.setAge(child.getAge() + 1);
                setAgeCategoriesAndCalculateAverage();
            }
            AnnualChanges annualChange = database.getAnnualChanges().get(i - 1);
            annualChange.addNewChildren(database);
            annualChange.updateChildren(database);
            annualChange.updateBudget(database);
            setAgeCategoriesAndCalculateAverage();
            database.removeYoungAdults();
            setAssignedBudgets();
            ElfModifier modifier = new ElfModifier(database);
            modifier.applyBlackPinkElfModifier();
            giveGifts(annualChange.getStrategy());
            Children giftedChildren = new Children();
            for (Child child : database.getInitialData().getChildren()) {
                OutputChild outputChild = new OutputChild(child);
                for (Gift receivedGift : child.getReceivedGifts()) {
                    OutputGift outputGift = new OutputGift(receivedGift);
                    outputChild.getReceivedGifts().add(outputGift);
                }
                giftedChildren.getChildren().add(outputChild);
            }
            odb.getAnnualChildren().add(giftedChildren);
            removeGifts();
        }
    }

    /**
     * Method goes through all the children from the database and the gift preferences of every
     * child. As long as the budget permits it, the cheapest gift from Santa's stash is
     * distributed to the child.
     */
    private void giveGifts(String strat) {
        GiftGivingStrategy giftStrategy = GiftGivingStrategyFactory.createStrategy(strat, database, giftList);
        if (giftStrategy != null) {
            giftStrategy.distributeGifts();
        }
    }

    /**
     * Method clears the list of received gifts for every child
     */
    private void removeGifts() {
        for (Child child : database.getInitialData().getChildren()) {
            child.getReceivedGifts().clear();
        }
    }

    /**
     * This method sets the age category for every child, then calculates the average score
     * using different strategies based on the age category
     */
    private void setAgeCategoriesAndCalculateAverage() {
        for (Child child : database.getInitialData().getChildren()) {
            child.setAgeCategory();
            CalculateScoreStrategy strategy = CalculateScoreStrategyFactory
                    .createStrategy(child.getAgeCategory(), child);
            if (strategy != null) {
                child.setAverageScore(strategy.getScore());
            }
            Double newAverage = child.getAverageScore();
            newAverage = newAverage + (newAverage * child.getNiceScoreBonus())/100;
            if (newAverage > 10.0) {
                child.setAverageScore(10.0);
             } else {
                child.setAverageScore(newAverage);
            }

        }
    }

    /**
     * Method calculates the budget unit for the year, then calculates the assigned budget for
     * every child in the database
     */
    private void setAssignedBudgets() {
        budgetUnit = database.getSantaBudget() / database.getSumOfAverage();
        for (Child child : database.getInitialData().getChildren()) {
            child.setAssignedBudget(budgetUnit * child.getAverageScore());
        }
    }

    private void setNiceScoreHistory() {
        for (Child child : database.getInitialData().getChildren()) {
            child.getNiceScoreHistory().add(child.getNiceScore());
        }
    }

}
