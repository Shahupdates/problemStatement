package org.lilyai;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageValidator {
    private static final String IMAGE_DELIMITER = "\\|\\|";

    public static boolean validate(String images) {
        // Split images using the delimiter
        String[] imageUrls = images.split(IMAGE_DELIMITER);

        // Validate each image URL
        for (String imageUrl : imageUrls) {
            boolean imageValid = validateImage(imageUrl);

            // If any image URL is invalid, return false
            if (!imageValid) {
                return false;
            }
        }

        return true;
    }

    private static boolean validateImage(String imageUrl) {
        try {
            // Check if the URL has a valid protocol
            if (!imageUrl.matches("^(http|https)://.*$")) {
                System.out.println("Invalid URL: " + imageUrl);
                return false;
            }

            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            System.out.println("URL: " + imageUrl + ", Response Code: " + responseCode);
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
