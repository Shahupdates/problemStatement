package org.lilyai;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ProductTypeValidator {
    private static final Map<Integer, String> productTypes = new HashMap<>();

    public static void setProductTypes(List<String[]> productTypeData) {
        for (String[] row : productTypeData) {
            int id = Integer.parseInt(row[0]);
            String productType = row[1];
            productTypes.put(id, productType);
        }
    }

    public static boolean validate(String productType) {
        // Compare with product types in the database and return validation result
        for (String dbProductType : productTypes.values()) {
            if (dbProductType.equalsIgnoreCase(productType)) {
                return true;
            }
        }
        return false;
    }
}
