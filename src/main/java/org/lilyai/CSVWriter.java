package org.lilyai;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public static void write(String filename, List<String[]> data) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Check if the status file exists, create it if necessary
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            for (String[] row : data) {
                writer.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
