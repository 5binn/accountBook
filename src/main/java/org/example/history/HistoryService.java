package org.example.history;

import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    String actionCode;
    String[] commandList;
    String readCommand;
    HistoryRepository historyRepository;

    public HistoryService() {
        historyRepository = new HistoryRepository();
    }
    public int create(String createDate, int categoryId, String content, int income, int expense) {
        return historyRepository.create(createDate, categoryId, content, income, expense);
    }
    public int sumIncome(String year, String month) {
        return historyRepository.sumIncome(year,month);
    }
    public int sumExpense() {
        return historyRepository.sumExpense();
    }
    public List<History> viewByMonth(String year, String month) {
        return historyRepository.viewByMonth(year, month);
    }
    public void viewByDay() {

    }
    public void viewByCategory() {

    }
    public void viewByAll() {

    }

    public void delete() {

    }

    public void update() {

    }
}
