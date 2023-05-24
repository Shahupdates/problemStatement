package org.lilyai;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVWriter {
    private static final Logger LOGGER = Logger.getLogger(CSVWriter.class.getName());

    public static void writeStatusFile(List<String[]> rows, String outputDirectory) {
        Path outputPath = Paths.get(outputDirectory, "status.csv");

        try (FileWriter writer = new FileWriter(outputPath.toFile())) {
            // Write header
            writer.append("id,display_name,status\n");

            for (String[] row : rows) {
                // Get the status for the row
                String status = ValidationUtils.determineRowStatus(row);

                // Write row with status
                writer.append(String.join(",", row)).append(",").append(status).append("\n");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write status file", e);
        }
    }
}
