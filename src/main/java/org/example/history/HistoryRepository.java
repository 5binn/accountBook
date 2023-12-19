package org.example.history;

import org.example.Global.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryRepository {

    public int create(String createDate, int categoryId, String content, int income, int expense) {
        String sql = String.format("INSERT INTO `history`" +
                "SET `date` = '%s'," +
                "`content` = '%s'," +
                "`income` = %d," +
                "`expense` = %d," +
                "categoryId = %d," +
                "accountId = %d," +
                "regDate = NOW()," +
                "modifyDate = NOW();", createDate, content, income, expense, categoryId, Container.getSeletedAccountBook().getId());
        return Container.getDbConnection().insert(sql);
    }

    public int sumIncome(String year, String month) {
        String sql = String.format("SELECT SUM(income) FROM history WHERE" +
                "YEAR(`date`) = %s AND MONTH(`date`) = %s %s" +
                "AND accountId = %d;", year, month, "", Container.getSeletedAccountBook().getId());
        Map<String, Object> row = Container.getDbConnection().selectRow(sql);
        History history = new History(row);
        return history.getIncome();
    }

    public int sumExpense() {
        return 0;
    }

    public List<History> viewByMonth(String year, String month) {
        List<History> historyList = new ArrayList<>();
        String sql = String.format("SELECT * FROM history WHERE" +
                "YEAR(`date`) = %s AND MONTH(`date`) = %s" +
                "AND accountId = %d ORDER BY `date` ASC;", year, month, Container.getSeletedAccountBook().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            History history = new History(row);
            historyList.add(history);
        }
        return historyList;
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
