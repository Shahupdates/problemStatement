package org.lilyai;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidationUtils {
    private static final Logger LOGGER = Logger.getLogger(ValidationUtils.class.getName());
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lilyDATA";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final int THREAD_POOL_SIZE = 10;

    public static void validateGender(List<String[]> rows) {
        List<String> validGenders = CsvReader.getValidGendersFromCSV();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM genders WHERE display_name = ?")) {
            boolean isFirstRow = true; // To skip the header row
            for (String[] row : rows) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                String gender = row[3].trim(); // Assuming gender column is at index 3

                // Skip the row if gender is empty or not valid
                if (gender.isEmpty() || !validGenders.contains(gender)) {
                    LOGGER.log(Level.INFO, "Invalid Gender: {0} for row: {1}", new Object[]{gender, String.join(",", row)});
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to validate genders", e);
        }
    }

    public static List<String> getValidGendersFromCSV() {
        List<String> validGenders = new ArrayList<>();

        try (Reader in = new FileReader("src/main/resources/data/Genders.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                String gender = record.get("display_name");
                validGenders.add(gender);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to get valid genders", e);
        }

        return validGenders;
    }

    public static boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("men") || gender.equalsIgnoreCase("women");
    }

    public static void validateProductType(List<String[]> rows) {
        List<String> validProductTypes = CsvReader.getValidProductTypesFromCSV();

        boolean isFirstRow = true; // To skip the header row
        for (String[] row : rows) {
            if (isFirstRow) {
                isFirstRow = false;
                continue;
            }

            String productType = row[4].trim(); // Assuming product type column is at index 4

            // Skip the row if product type is empty or not valid
            if (productType.isEmpty() || !validProductTypes.contains(productType)) {
                LOGGER.log(Level.INFO, "Invalid Product Type: {0} for row: {1}", new Object[]{productType, String.join(",", row)});
            }
        }
    }

    public static List<String> getValidProductTypesFromCSV() {
        List<String> validProductTypes = new ArrayList<>();

        try (Reader in = new FileReader("src/main/resources/data/ProductTypes.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                String productType = record.get("display_name");
                validProductTypes.add(productType);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to get valid product types", e);
        }

        return validProductTypes;
    }

    public static void validateImages(List<String[]> rows) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<?>> imageValidationFutures = new ArrayList<>();

        boolean isFirstRow = true; // To skip the header row
        for (String[] row : rows) {
            if (isFirstRow) {
                isFirstRow = false;
                continue;
            }

            String images = row[5].trim(); // Assuming images column is at index 5

            // Skip the row if images is empty
            if (images.isEmpty()) {
                continue;
            }

            String[] imageUrls = images.split("\\|\\|");
            for (String imageUrl : imageUrls) {
                imageUrl = imageUrl.trim();

                // Skip the image URL if it is empty or does not have a valid protocol
                if (imageUrl.isEmpty() || !isValidProtocol(imageUrl)) {
                    continue;
                }

                ImageValidationTask task = new ImageValidationTask(imageUrl, row);
                Future<?> future = executorService.submit(task);
                imageValidationFutures.add(future);
            }
        }

        // Wait for all image validation tasks to complete
        for (Future<?> future : imageValidationFutures) {
            try {
                future.get();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to validate images", e);
            }
        }

        // Shutdown the executor service
        executorService.shutdown();
    }

    private static boolean isValidProtocol(String url) {
        return url.matches("^https?://.*$");
    }

    public static String determineRowStatus(String[] row) {
        StringBuilder statusBuilder = new StringBuilder();

        // Skip the first row (header row)
        if (row[0].equalsIgnoreCase("id")) {
            return ""; // Empty string for header row
        }

        // Gender validation
        String gender = row[3].trim(); // Assuming gender column is at index 3
        if (!isValidGender(gender)) {
            statusBuilder.append("Invalid Gender; ");
        }

        // Product Type validation
        String productType = row[4].trim(); // Assuming product type column is at index 4
        if (productType.isEmpty() || !getValidProductTypesFromCSV().contains(productType)) {
            statusBuilder.append("Invalid Product Type; ");
        }

        // Image validation
        String images = row[5].trim(); // Assuming images column is at index 5
        String[] imageUrls = images.split("\\|\\|");
        for (String imageUrl : imageUrls) {
            imageUrl = imageUrl.trim();

            // Skip the image URL if it is empty or does not have a valid protocol
            if (imageUrl.isEmpty() || !isValidProtocol(imageUrl)) {
                continue;
            }

            if (!isValidImageUrl(imageUrl)) {
                statusBuilder.append("Invalid Image URL: ").append(imageUrl).append("; ");
                break;
            }
        }

        if (statusBuilder.length() == 0) {
            return "Valid";
        } else {
            return statusBuilder.toString();
        }
    }

    public static boolean isValidImageUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int statusCode = connection.getResponseCode();

            return statusCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to validate image URL: " + imageUrl, e);
            return false;
        }
    }
}
