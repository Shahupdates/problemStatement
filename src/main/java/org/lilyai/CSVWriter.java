package org.lilyai;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVWriter {
    private static final Logger LOGGER = Logger.getLogger(CSVWriter.class.getName());

    public static void writeStatusFile(List<String[]> rows, String outputFile) {
        try (FileWriter writer = new FileWriter(outputFile)) {
            // Write header


            for (String[] row : rows) {
                // Get the status for the row
                String status = ValidationUtils.determineRowStatus(row);

                // Write row with status
                writer.append(String.join(",", row) + "," + status + "\n");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write status file", e);
        }
    }
}
