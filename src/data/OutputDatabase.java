package data;

import entities.Children;

import java.util.ArrayList;
import java.util.List;

public final class OutputDatabase {

    private static data.OutputDatabase outputDatabase = null;
    private List<Children> annualChildren;

    public OutputDatabase() {
        annualChildren = new ArrayList<>();
    }

    /**
     * Lazy Singleton implementation of output database
     * @return instance of output database
     */
    public static data.OutputDatabase getInstance() {
        if (outputDatabase == null) {
            outputDatabase = new data.OutputDatabase();
        }
        return outputDatabase;
    }

    public List<Children> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final List<Children> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public static data.OutputDatabase getOutputDatabase() {
        return outputDatabase;
    }

    public static void setOutputDatabase(final data.OutputDatabase outputDatabase) {
        data.OutputDatabase.outputDatabase = outputDatabase;
    }
}
