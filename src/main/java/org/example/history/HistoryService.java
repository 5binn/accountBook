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

    public int sumIncome(String year, String month, String day, String categoryId) {
        String yearSQL;
        String monthSQL;
        String daySQL;
        String categorySQL;

        if (year.isEmpty()) {
            yearSQL = year;
        } else yearSQL = String.format("AND YEAR(`date`) = '%s'", year);

        if (month.isEmpty()) {
            monthSQL = month;
        } else monthSQL = String.format("AND MONTH(`date`) = '%s'", month);

        if (day.isEmpty()) {
            daySQL = day;
        } else daySQL = String.format("AND DAY(`date`) = '%s'", day);

        if (categoryId.isEmpty()) {
            categorySQL = categoryId;
        }else categorySQL = String.format("AND categoryId = %s", categoryId);

        return historyRepository.sumIncome(yearSQL, monthSQL, daySQL, categorySQL);
    }

    public int sumExpense(String year, String month, String day, String categoryId) {
        String yearSQL;
        String monthSQL;
        String daySQL;
        String categorySQL;

        if (year.isEmpty()) {
            yearSQL = year;
        } else yearSQL = String.format("AND YEAR(`date`) = '%s'", year);

        if (month.isEmpty()) {
            monthSQL = month;
        } else monthSQL = String.format("AND MONTH(`date`) = '%s'", month);

        if (day.isEmpty()) {
            daySQL = day;
        } else daySQL = String.format("AND DAY(`date`) = '%s'", day);

        if (categoryId.isEmpty()) {
            categorySQL = categoryId;
        }else categorySQL = String.format("AND categoryId = %s", categoryId);

        return historyRepository.sumExpense(yearSQL, monthSQL, daySQL, categorySQL);
    }

    public List<HistoryDTO> viewByMonth(String year, String month) {
        return historyRepository.viewByMonth(year, month);
    }

    public List<HistoryDTO> viewByDay(String year, String month, String day) {
        return historyRepository.viewByDay(year, month, day);
    }

    public List<HistoryDTO> viewByCategory(String categoryId) {
        return historyRepository.viewByCategory(categoryId);
    }

    public List<HistoryDTO> viewByAll() {
        return historyRepository.viewByAll();
    }

    public void delete() {

    }

    public void update() {

    }
}
