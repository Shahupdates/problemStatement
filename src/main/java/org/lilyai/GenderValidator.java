package org.lilyai;

import java.util.HashMap;
import java.util.Map;

public class GenderValidator {
    private static final Map<Integer, String> genders = new HashMap<>();

    static {
        // Add gender mappings from the database
        genders.put(0, "Women");
        genders.put(1, "Men");
        genders.put(2, "Boys");
        genders.put(3, "Girls");
        genders.put(4, "Unknown Person Type");
        genders.put(5, "Unisex");
        genders.put(10, "Infant Boys");
        genders.put(11, "Infant Girls");
        genders.put(12, "Not Applicable");
    }

    public static boolean validate(String gender) {
        // Compare with genders in the database and return validation result
        for (String dbGender : genders.values()) {
            if (dbGender.equalsIgnoreCase(gender)) {
                return true;
            }
        }
        return false;
    }
}
