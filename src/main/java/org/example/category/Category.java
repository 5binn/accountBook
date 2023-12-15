package org.example.category;

import java.util.Map;

public class Category {
    int categoryId;
    String category;

    Category(Map<String, Object> row) {
        this.categoryId = (int) row.get("categoryId");
        this.category = (String) row.get("category");
    }

}
