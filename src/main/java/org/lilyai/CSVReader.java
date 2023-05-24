package org.lilyai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReader {
    private static final Logger LOGGER = Logger.getLogger(CSVReader.class.getName());

    public static List<String[]> readInputFile(String inputFile) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(CSVReader.class.getClassLoader().getResourceAsStream(inputFile)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                rows.add(row);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading input file", e);
        }

        return rows;
    }
}
