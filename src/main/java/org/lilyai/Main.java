package org.lilyai;

import javax.xml.validation.Validator;
import java.util.List;

public class Main {
    private static final String INPUT_FILE = "input.csv";
    private static final String STATUS_FILE = "status.csv";

    public static void main(String[] args) {
        List<String[]> inputData = CSVReader.read(INPUT_FILE);
        List<String[]> statusData = Validator.validateAndCreateStatus(inputData);
        CSVWriter.write(STATUS_FILE, statusData);
    }
}
