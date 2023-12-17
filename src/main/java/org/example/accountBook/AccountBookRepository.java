package org.example.accountBook;

import org.example.Global.Container;
import org.example.member.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountBookRepository {

    public String create(String accountName, int balance, int savingGoal, String memberIds) {
        String sql = String.format("INSERT INTO `accountBook`" +
                "SET memberId = %d," +
                "`accountName` = '%s'," +
                "`balance` = %d," +
                "`savingGoal` = %d," +
                "memberIds = '%s'," +
                "regDate = now()," +
                "modifyDate = now();", Container.getLoggedInMember().getId(), accountName, balance, savingGoal, memberIds);
        int id = Container.getDbConnection().insert(sql);
        List<AccountBook> accountBookList = this.findByAll();
        return Container.getLoggedInMember().getUserName();
    }

    public void select(AccountBook accountBook) {
        Container.setSeletedAccountBook(accountBook);
    }

    public void cancel() {
        Container.setSeletedAccountBook(null);
    }

    public void delete(AccountBook accountBook) {
        String sql = String.format("DELETE FROM `accountBook` WHERE accountName = '%s';", accountBook.getAccountName());
        Container.getDbConnection().delete(sql);
    }

    public String modify(String modifyName, int balance, int savingGoal, String regDate) {
        String sql = String.format("UPDATE `accountBook`" +
                        "SET memberId = %d," +
                        "accountName = '%s'," +
                        "balance = %d, " +
                        "savingGoal = %d," +
                        "memberIds = '%s'," +
                        "regDate = '%s'," +
                        "modifyDate = NOW()" +
                        "WHERE accountName = '%s';", Container.getLoggedInMember().getId(), modifyName, balance, savingGoal,
                Container.getLoggedInMember().getUserName(), regDate, modifyName);
        int id = Container.getDbConnection().delete(sql);
        List<AccountBook> accountBookList = this.findByAll();
        return accountBookList.get(id - 1).getAccountName();
    }

    public List<AccountBook> findByAll() {
        List<AccountBook> accountBookList = new ArrayList<>();
        String sql = "SELECT * FROM `accountBook`;";
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            AccountBook accountBook = new AccountBook(row);
            accountBookList.add(accountBook);
        }
        return accountBookList;
    }
}
