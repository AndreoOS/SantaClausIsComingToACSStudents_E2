package dataprocessing;

import actions.Simulation;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import data.Database;
import data.GiftList;
import data.OutputDatabase;

import java.io.File;
import java.io.IOException;

public final class DataManagement {

    public DataManagement() {
        // constructor for json
    }

    /**
     * Private method that calls method for simulating the years using the Simulation class
     * @param db Database containing all the relevant data from the files
     * @param odb Database which will contain the result
     * @param giftList Class that stores all the gifts based on the category they're in
     */
    private void simulateData(final Database db, final OutputDatabase odb,
                             final GiftList giftList) {
        Simulation sim = new Simulation(db, giftList);
        sim.simulateAll(odb);
    }

    /**
     * Method reads data from every test in order and that data is stored in a singleton database
     * Initializes a new singleton output database and a gift list that help my implementation
     * Calls method to simulate all the years then the method to write the result to a json file
     * Finally, the output database is nullified, so as the results don't concatenate together
     */
    public void readAllDataAndSimulate() {
        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            Database db = Database.getInstance();
            try {
                db = new ObjectMapper().readerFor(Database.class).readValue(
                        new File("tests/test" + i + Constants.FILE_EXTENSION));
                OutputDatabase odb = OutputDatabase.getInstance();
                GiftList giftList = new GiftList();
                giftList.populateGiftList(db.getInitialData().getSantaGiftsList());
                simulateData(db, odb, giftList);
                writeAllData(odb, i);
                OutputDatabase.setOutputDatabase(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Private method that writes the data from the output database to a JSON file
     * @param odb Database that contains the results after sim
     * @param testNumber Number of the test -- used to name the file
     */
    private void writeAllData(final OutputDatabase odb,
                             final Integer testNumber) {
        ObjectMapper om = new ObjectMapper();
        try {
            om.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(
                            Constants.OUTPUT_PATH + testNumber + Constants.FILE_EXTENSION), odb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
