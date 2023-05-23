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
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                inputData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputData;
    }
}
