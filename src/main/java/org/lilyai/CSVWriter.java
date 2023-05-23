package org.lilyai;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public static void write(String filename, List<String[]> data) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String[] row : data) {
                writer.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}