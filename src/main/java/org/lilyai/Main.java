    package org.lilyai;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
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
            List<String[]> statusData = org.lilyai.Validator.validateAndCreateStatus(inputData);

            // Step 5: Write the status data to the status file
            CSVWriter.write(STATUS_FILE, statusData);

            // Step 6: Populate the database tables
            populateDatabaseTables(genderData, productTypeData);
        }

        private static void populateDatabaseTables(List<String[]> genderData, List<String[]> productTypeData) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lilyData", "your_username", "your_password")) {
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
