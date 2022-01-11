package actions;

import data.Database;
import entities.Child;
import enums.ElvesType;

public class ElfModifier {
    Database database;

    public ElfModifier(Database database) {
        this.database = database;
    }

    public void applyBlackPinkElfModifier() {
        for (Child child : database.getInitialData().getChildren()) {
            if (child.getElf().equals(ElvesType.BLACK)) {
                Double newBudget = child.getAssignedBudget();
                newBudget -= ((newBudget * 30) / 100);
                child.setAssignedBudget(newBudget);
            } else if (child.getElf().equals(ElvesType.PINK)) {
                Double newBudget = child.getAssignedBudget();
                newBudget += ((newBudget * 30) / 100);
                child.setAssignedBudget(newBudget);
            }
        }
    }
}
