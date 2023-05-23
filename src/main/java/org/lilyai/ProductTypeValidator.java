package org.lilyai;

import java.util.HashMap;
import java.util.Map;

public class ProductTypeValidator {
    private static final Map<Integer, String> productTypes = new HashMap<>();

    static {
        // Add product type mappings from the database
        productTypes.put(9, "Gifting Accessories");
        productTypes.put(27, "Unknown Product Type");
        productTypes.put(29, "Sets");
        productTypes.put(56, "Equipment Gear");
        productTypes.put(65, "Unprocessed");
        // Add more product types...
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

