package org.lilyai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<String[]> read(String filename) {
        List<String[]> inputData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read the header row
            String headerLine = reader.readLine();
            String[] columnNames = parseRow(headerLine);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = parseRow(line);
                inputData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputData;
    }

    private static String[] parseRow(String line) {
        // Split the line by comma, ignoring any commas within quotes
        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        // Remove quotes from each part
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replaceAll("^\"|\"$", "");
        }

        return parts;
    }
}
