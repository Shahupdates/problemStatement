package org.lilyai;

import javax.xml.validation.Validator;
import java.util.List;

public class Main {
    private static final String INPUT_FILE = "input.csv";
    private static final String STATUS_FILE = "status.csv";
    private static final String GENDER_FILE = "genders.csv";
    private static final String PRODUCT_TYPE_FILE = "productTypes.csv";

    public static void main(String[] args) {
        // Step 1: Read the input file
        List<String[]> inputData = CSVReader.read(INPUT_FILE);

        // Step 2: Read the gender file
        List<String[]> genderData = CSVReader.read(GENDER_FILE);
        GenderValidator.setGenders(genderData);

        // Step 3: Read the product type file
        List<String[]> productTypeData = CSVReader.read(PRODUCT_TYPE_FILE);
        ProductTypeValidator.setProductTypes(productTypeData);

        // Step 4: Perform validations and create the status file
        List<String[]> statusData = Validator.validateAndCreateStatus(inputData);

        // Step 5: Write the status data to the status file
        CSVWriter.write(STATUS_FILE, statusData);
    }
}
