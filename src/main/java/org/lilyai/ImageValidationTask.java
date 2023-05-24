package org.lilyai;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageValidationTask implements Callable<Void> {
    private static final Logger LOGGER = Logger.getLogger(ImageValidationTask.class.getName());
    private final String imageUrl;
    private final String[] row;

    public ImageValidationTask(String imageUrl, String[] row) {
        this.imageUrl = imageUrl;
        this.row = row;
    }

    @Override
    public Void call() {
        LOGGER.log(Level.INFO, "Validating image URL: {0}", imageUrl);
        if (!ValidationUtils.isValidImageUrl(imageUrl)) {
            LOGGER.log(Level.INFO, "Invalid Image URL: {0} for row: {1}", new Object[]{imageUrl, String.join(",", row)});
        } else {
            LOGGER.log(Level.INFO, "Valid Image URL: {0}", imageUrl);
        }
        return null;
    }
}
