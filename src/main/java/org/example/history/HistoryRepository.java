package org.example.history;

import org.example.Global.Container;

public class HistoryRepository {

    public int create(String createDate, int categoryId, String content,int income, int expense) {
        String sql = String.format("INSERT INTO `history`" +
                "SET `date` = '%s'," +
                "`content` = '%s'," +
                "`income` = %d," +
                "`expense` = %d," +
                "categoryId = %d," +
                "accountId = %d," +
                "regDate = NOW()," +
                "modifyDate = NOW();",createDate,content,income,expense,categoryId,Container.getSeletedAccountBook().getId());
        return Container.getDbConnection().insert(sql);
    }
    public void read() {

    }
    public void delete() {

    }
    public void update() {

    }
}
