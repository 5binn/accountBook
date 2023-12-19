package org.example.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@AllArgsConstructor
@Setter
@Getter
public class HistoryDTO {
    String date;
    String content;
    int income;
    int expense;
    String category;

    HistoryDTO(Map<String, Object> row) {
        this.date = row.get("date").toString();
        this.content = (String) row.get("content");
        this.income = (int) row.get("income");
        this.expense = (int) row.get("expense");
        this.category = (String) row.get("category");
    }
}
