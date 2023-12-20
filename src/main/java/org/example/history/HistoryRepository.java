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

    public int sumIncome(String year, String month, String day, String categoryId) {
        String sql = String.format("SELECT SUM(income) FROM history WHERE " +
                "accountId = %d %s %s %s %s;", Container.getSeletedAccountBook().getId(),
                 year, month, day, categoryId);
        return Container.getDbConnection().selectRowIntValue(sql);
    }

    public int sumExpense(String year, String month, String day, String categoryId) {
        String sql = String.format("SELECT SUM(expense) FROM history WHERE " +
                        "accountId = %d %s %s %s %s;", Container.getSeletedAccountBook().getId(),
                year, month, day, categoryId);
        return Container.getDbConnection().selectRowIntValue(sql);
    }

    public List<HistoryDTO> viewByMonth(String year, String month) {
        List<HistoryDTO> historyDTOList = new ArrayList<>();
        String sql = String.format("SELECT H.`date`, C.category, H.content, H.income, H.expense " +
                        "FROM history AS H JOIN category AS C ON H.categoryId = C.categoryId " +
                        "WHERE YEAR(`date`) = '%s' AND MONTH(`date`) = '%s' AND accountId = %d ORDER BY `date` ASC;",
                year, month, Container.getSeletedAccountBook().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        if (rows == null) return null;
        for (Map<String, Object> row : rows) {
            HistoryDTO historyDTO = new HistoryDTO(row);
            historyDTOList.add(historyDTO);
        }
        return historyDTOList;
    }

    public List<HistoryDTO> viewByDay(String year, String month, String day) {
        List<HistoryDTO> historyDTOList = new ArrayList<>();
        String sql = String.format("SELECT H.`date`, C.category, H.content, H.income, H.expense " +
                        "FROM history AS H JOIN category AS C ON H.categoryId = C.categoryId " +
                        "WHERE YEAR(`date`) = '%s' AND MONTH(`date`) = '%s' " +
                        "AND DAY(`date`) = '%s' AND accountId = %d ORDER BY `date` ASC;",
                year, month, day, Container.getSeletedAccountBook().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        if (rows == null) return null;
        for (Map<String, Object> row : rows) {
            HistoryDTO historyDTO = new HistoryDTO(row);
            historyDTOList.add(historyDTO);
        }
        return historyDTOList;
    }

    public List<HistoryDTO> viewByCategory(String categoryId) {
        List<HistoryDTO> historyDTOList = new ArrayList<>();
        String sql = String.format("SELECT H.`date`, C.category, H.content, H.income, H.expense " +
                        "FROM history AS H JOIN category AS C ON H.categoryId = C.categoryId " +
                        "WHERE H.categoryId = %s AND accountId = %d ORDER BY `date` ASC;",
                categoryId, Container.getSeletedAccountBook().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        if (rows == null) return null;
        for (Map<String, Object> row : rows) {
            HistoryDTO historyDTO = new HistoryDTO(row);
            historyDTOList.add(historyDTO);
        }
        return historyDTOList;
    }

    public List<HistoryDTO> viewByAll() {
        List<HistoryDTO> historyDTOList = new ArrayList<>();
        String sql = String.format("SELECT H.`date`, C.category, H.content, H.income, H.expense " +
                        "FROM history AS H JOIN category AS C ON H.categoryId = C.categoryId " +
                        "WHERE accountId = %d ORDER BY `date` ASC;", Container.getSeletedAccountBook().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        if (rows == null) return null;
        for (Map<String, Object> row : rows) {
            HistoryDTO historyDTO = new HistoryDTO(row);
            historyDTOList.add(historyDTO);
        }
        return historyDTOList;
    }

    public void delete(String deleteDate, String deleteContent) {
        String sql = String.format("DELETE FROM `history` WHERE `date` = '%s' AND content = '%s';", deleteDate, deleteContent);
        Container.getDbConnection().delete(sql);
    }

    public int update(String updateDate, int categoryId, String content, int income, int expense, String findDate, String findContent) {
        String sql = String.format("UPDATE `history`" +
                "SET `date` = %s," +
                "content = '%s'," +
                "income = %d," +
                "expense = %d," +
                "categoryId = %d," +
                "regDate = NOW()," +
                "modifyDate = NOW()" +
                "WHERE `date` = '%s' AND content = '%s';", updateDate, content, income, expense, categoryId, findDate, findContent);
        return Container.getDbConnection().insert(sql);
    }

    public HistoryDTO findHistory(String findDate, String findContent) {
        String sql = String.format("SELECT H.`date` , C.category ,H.content , H.income , H.expense " +
                "FROM history AS H JOIN category AS C ON H.categoryId = C.categoryId WHERE `date` = '%s' AND content = '%s'; ", findDate, findContent);
        Map<String, Object> row = Container.getDbConnection().selectRow(sql);
        return new HistoryDTO(row);
    }
}
