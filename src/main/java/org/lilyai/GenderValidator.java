package org.lilyai;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GenderValidator {
    private static final Map<Integer, String> genders = new HashMap<>();

    public static void setGenders(List<String[]> genderData) {
        for (int i = 1; i < genderData.size(); i++) { // Start from index 1 to skip the header row
            String[] row = genderData.get(i);
            int id = Integer.parseInt(row[0]);
            String gender = row[1];
            genders.put(id, gender);
        }
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
