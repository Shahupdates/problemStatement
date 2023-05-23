package org.lilyai;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static 
    List<String[]> validateAndCreateStatus(List<String[]> inputData) {
        List<String[]> statusData = new ArrayList<>();

        // Iterate through each row of input data
        for (String[] row : inputData) {
            String status;
            StringBuilder errorMessages = new StringBuilder();

            // Perform gender validation
            String gender = row[0];  // Replace with the appropriate column index
            if (GenderValidator.validate(gender)) {
                // Gender is valid
                status = "Valid";
            } else {
                // Gender is invalid
                status = "Invalid";
                errorMessages.append("Invalid gender. ");
            }

            // Perform product type validation
            String productType = row[1];  // Replace with the appropriate column index
            if (ProductTypeValidator.validate(productType)) {
                // Product type is valid
                status = "Valid";
            } else {
                // Product type is invalid
                status = "Invalid";
                errorMessages.append("Invalid product type. ");
            }

            // Perform image validation
            String images = row[2];  // Replace with the appropriate column index
            if (validateImages(images)) {
                // Images are valid
                status = "Valid";
            } else {
                // Images are invalid
                status = "Invalid";
                errorMessages.append("Invalid images. ");
            }

            // Create a new row with the status and error messages
            String[] statusRow = new String[row.length + 2];
            System.arraycopy(row, 0, statusRow, 0, row.length);
            statusRow[row.length] = status;
            statusRow[row.length + 1] = errorMessages.toString();
            statusData.add(statusRow);
        }

        return statusData;
    }

    private static boolean validateImages(String images) {
        // Implement image validation logic here
        // Return true if all images are valid, false otherwise
        return true;  // Replace with your implementation
    }
}

