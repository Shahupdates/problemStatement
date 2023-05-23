package org.lilyai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lilyData";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";
    private static final String PRODUCT_TYPE_FILE = "ProductTypes.csv";
    private static final String GENDER_FILE = "Genders.csv";

    public static void main(String[] args) {
        // Step 1: Read the CSV files
        List<String[]> productTypeData = CSVReader.read(PRODUCT_TYPE_FILE);
        List<String[]> genderData = CSVReader.read(GENDER_FILE);

        // Step 2: Establish a database connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Step 3: Populate the product_types table
            populateProductTypes(connection, productTypeData);

            // Step 4: Populate the genders table
            populateGenders(connection, genderData);
        } catch (SQLException e) {
            e.printStackTrace();
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
}
