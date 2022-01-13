package actions;

import elfmodifiers.BlackElf;
import elfmodifiers.ModifierVisitor;
import elfmodifiers.PinkElf;
import elfmodifiers.YellowElf;
import common.Constants;
import data.AnnualChanges;
import data.Database;
import data.GiftList;
import data.OutputDatabase;
import dataprocessing.scorestrategy.CalculateScoreStrategy;
import dataprocessing.giftstrategy.GiftGivingStrategy;
import entities.Child;
import entities.Children;
import entities.OutputChild;
import factory.CalculateScoreStrategyFactory;
import factory.GiftGivingStrategyFactory;

public final class Simulation {
    private final Database database;
    private final GiftList giftList;

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
        for (Child child : database.getInitialData().getChildren()) {
            child.eraseDuplicates();
        }
        setNiceScoreHistory();
        setAgeCategoriesAndCalculateAverage();
        database.removeYoungAdults();
        setAssignedBudgets();
        BlackElf blackElf = new BlackElf(database);
        PinkElf pinkElf = new PinkElf(database);
        ModifierVisitor modifierVisitor = new ModifierVisitor();
        blackElf.accept(modifierVisitor);
        pinkElf.accept(modifierVisitor);
        giveGifts("id");
        YellowElf yellowElf = new YellowElf(database, giftList);
        yellowElf.accept(modifierVisitor);
        Children giftedChildren = new Children();
        for (Child child : database.getInitialData().getChildren()) {
            OutputChild outputChild = new OutputChild(child);
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
            annualChange.addNewGifts(database, giftList);
            setAgeCategoriesAndCalculateAverage();
            database.removeYoungAdults();
            setAssignedBudgets();
            BlackElf blackElf = new BlackElf(database);
            PinkElf pinkElf = new PinkElf(database);
            ModifierVisitor modifierVisitor = new ModifierVisitor();
            blackElf.accept(modifierVisitor);
            pinkElf.accept(modifierVisitor);
            giveGifts(annualChange.getStrategy());
            YellowElf yellowElf = new YellowElf(database, giftList);
            yellowElf.accept(modifierVisitor);
            Children giftedChildren = new Children();
            for (Child child : database.getInitialData().getChildren()) {
                OutputChild outputChild = new OutputChild(child);
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
    private void giveGifts(final String strategy) {
        GiftGivingStrategy giftStrategy = GiftGivingStrategyFactory
                .createStrategy(strategy, database, giftList);
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
            newAverage = newAverage
                    + (newAverage * child.getNiceScoreBonus()) / Constants.MAX_PERCENT;
            if (newAverage > Constants.MAX_NICE_SCORE) {
                child.setAverageScore(Constants.MAX_NICE_SCORE);
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
        Double budgetUnit = database.getSantaBudget() / database.getSumOfAverage();
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
