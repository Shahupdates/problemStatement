package org.lilyai;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lilyDATA";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) throws SQLException, IOException {
        try {
            String inputFile = "src/main/resources/data/InputFile.csv";
            String outputFile = "src/main/resources/data/status.csv";

            List<String[]> rows = CsvReader.readInputFile(inputFile);

            // Perform validations
            ValidationUtils.validateGender(rows);
            ValidationUtils.validateProductType(rows);
            ValidationUtils.validateImages(rows);

            // Write status file
            CsvWriter.writeStatusFile(rows, outputFile);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error occurred", e);
        }
    }
}
