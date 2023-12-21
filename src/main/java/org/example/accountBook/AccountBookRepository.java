package org.example.accountBook;

import org.example.Global.Container;

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
        Container.getDbConnection().insert(sql);
        return Container.getLoggedInMember().getUserName();
    }

    public void select(AccountBook accountBook) {
        Container.setSeletedAccountBook(accountBook);
    }

    public void cancel() {
        Container.setSeletedAccountBook(null);
    }

    public void share(String shareId) {
        String sql = String.format("UPDATE `accountBook`" +
                        "SET memberId = %d," +
                        "accountName = '%s'," +
                        "balance = %d, " +
                        "savingGoal = %d," +
                        "memberIds = '%s,%s'," +
                        "regDate = '%s'," +
                        "modifyDate = NOW()" +
                        "WHERE accountName = '%s';", Container.getLoggedInMember().getId(),
                Container.getSeletedAccountBook().getAccountName(),
                Container.getSeletedAccountBook().getBalance(),
                Container.getSeletedAccountBook().getSavingGoal(),
                Container.getSeletedAccountBook().getMemberIds(), shareId,
                Container.getSeletedAccountBook().getRegDate(),
                Container.getSeletedAccountBook().getAccountName());
        Container.getDbConnection().update(sql);
    }

    public void delete(AccountBook accountBook) {
        String sql = String.format("DELETE FROM `accountBook` WHERE accountName = '%s';", accountBook.getAccountName());
        Container.getDbConnection().delete(sql);
    }

    public String modify(String modifyName, int balance, int savingGoal, String modifyDate, String accountName) {
        String sql = String.format("UPDATE `accountBook`" +
                        "SET memberId = %d," +
                        "accountName = '%s'," +
                        "balance = %d," +
                        "savingGoal = %d," +
                        "memberIds = '%s'," +
                        "regDate = '%s'," +
                        "modifyDate = NOW()" +
                        "WHERE accountName = '%s';", Container.getLoggedInMember().getId(), modifyName, balance, savingGoal,
                Container.getLoggedInMember().getUserName(), modifyDate, accountName);
        Container.getDbConnection().update(sql);
        //List<AccountBook> accountBookList = this.findByAll();
        return this.findByAccount(modifyName);
    }

    public List<AccountBook> findByAll() {
        List<AccountBook> accountBookList = new ArrayList<>();
        String sql = "SELECT * FROM `accountBook`;";
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            AccountBook accountBook = new AccountBook(row);
            String [] authority = accountBook.getMemberIds().split(",");
            for (String id : authority) {
                if (Container.getLoggedInMember().getUserName().equals(id)) {
                    accountBookList.add(accountBook);
                }
            }
        }
        return accountBookList;
    }

    public String findByAccount(String accountName) {
        String sql = String.format("SELECT * FROM `accountBook` WHERE accountName = '%s';", accountName);
        Map<String, Object> row = Container.getDbConnection().selectRow(sql);
        AccountBook accountBook = new AccountBook(row);
        return accountBook.getAccountName();
    }

    /*public void authorityCheck() {
        List<AccountBook> accountBookList = this.findByAll();
        for (AccountBook accountBook : accountBookList) {
            String [] authority = accountBook.getMemberIds().split(",");
            for (String id : authority) {
                if (Container.getLoggedInMember().getUserName().equals(id)) {

                }
            }
        }
        String authority = Container.getSelectedAccountBook().getMemberIds();
        String[] memberList = authority.split(",");
    }*/
}
