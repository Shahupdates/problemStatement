package org.lilyai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String INPUT_FILE = "src/main/resources/data/inputfile.csv";
    private static final String STATUS_FILE = "src/main/resources/data/status.csv";
    private static final String GENDER_FILE = "src/main/resources/data/genders.csv";
    private static final String PRODUCT_TYPE_FILE = "src/main/resources/data/producttypes.csv";

    public static void main(String[] args) {
        // Step 1: Read the input file
        List<String[]> inputData = CSVReader.read(INPUT_FILE);

        // Step 2: Read the gender file
        List<String[]> genderData = CSVReader.read(GENDER_FILE);
        GenderValidator.setGenders(genderData);

        // Step 3: Read the product type file
        List<String[]> productTypeData = CSVReader.read(PRODUCT_TYPE_FILE);
        ProductTypeValidator.setProductTypes(productTypeData);

        // Step 4: Perform validations and create the status data
        List<String[]> statusData = createStatusData(inputData);

        // Step 5: Write the status data to the status file
        CSVWriter.write(STATUS_FILE, statusData);

        // Step 6: Populate the database tables
        populateDatabaseTables(genderData, productTypeData);
    }

    private static List<String[]> createStatusData(List<String[]> inputData) {
        List<String[]> statusData = new ArrayList<>();

        // Iterate through each row of input data
        for (String[] row : inputData) {
            String[] statusRow = new String[row.length + 1];
            System.arraycopy(row, 0, statusRow, 0, row.length);

            // Perform validations and determine the status
            String validationStatus = validateRow(row);
            statusRow[row.length] = validationStatus;

            // Add the status row to the status data
            statusData.add(statusRow);
        }

        return statusData;
    }

    private static String validateRow(String[] row) {
        // Perform validations for the row
        boolean isValid = true;
        StringBuilder errorMessages = new StringBuilder();

        String gender = row[0];  // Replace with the appropriate column index
        if (!GenderValidator.validate(gender)) {
            isValid = false;
            errorMessages.append("Invalid gender. ");
        }

        String productType = row[1];  // Replace with the appropriate column index
        if (!ProductTypeValidator.validate(productType)) {
            isValid = false;
            errorMessages.append("Invalid product type. ");
        }

        String images = row[2];  // Replace with the appropriate column index
        if (!ImageValidator.validate(images)) {
            isValid = false;
            errorMessages.append("Invalid images. ");
        }

        if (isValid) {
            return "Valid";
        } else {
            return errorMessages.toString();
        }
    }

    private static void populateDatabaseTables(List<String[]> genderData, List<String[]> productTypeData) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lilyDATA", "root", "root")) {
            // Step 6a: Populate the genders table
            populateGenders(connection, genderData);

            // Step 6b: Populate the product_types table
            populateProductTypes(connection, productTypeData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void populateGenders(Connection connection, List<String[]> data) throws SQLException {
        String insertQuery = "INSERT INTO genders (id, display_name) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            for (String[] row : data) {
                statement.setInt(1, Integer.parseInt(row[0]));
                statement.setString(2, row[1]);
                statement.executeUpdate();
            }
        }
    }

    private static void populateProductTypes(Connection connection, List<String[]> data) throws SQLException {
        String insertQuery = "INSERT INTO product_types (id, display_name, vertical_display_name) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            for (String[] row : data) {
                statement.setInt(1, Integer.parseInt(row[0]));
                statement.setString(2, row[1]);
                statement.setString(3, row[2]);
                statement.executeUpdate();
            }
        }
    }
}
