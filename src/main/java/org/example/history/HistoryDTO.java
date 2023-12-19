package org.example.history;

import java.util.Map;

public class HistoryDTO {
    String date;
    String content;
    int income;
    int expense;
    String category;

    HistoryDTO(Map<String, Object> row) {
        this.date = (String) row.get("date");
        this.content = (String) row.get("content");
        this.income = (int) row.get("income");
        this.expense = (int) row.get("expense");
        this.category = (String) row.get("category");
    }
}
