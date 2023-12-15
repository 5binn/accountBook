package org.example.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
public class History {
    int id;
    String date;
    String content;
    int income;
    int expense;
    int categoryId;
    int accountId;
    String regDate;
    String modifyDate;

    History(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.date = (String) row.get("date");
        this.content = (String) row.get("content");
        this.income = (int) row.get("income");
        this.expense = (int) row.get("expense");
        this.categoryId = (int) row.get("categoryId");
        this.accountId = (int) row.get("accountId");
        this.regDate = row.get("regDate").toString();
        this.modifyDate = row.get("modifyDate").toString();
    }
}
