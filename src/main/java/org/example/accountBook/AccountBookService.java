package org.example.accountBook;

import org.example.Global.Container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AccountBookService {
    private AccountBookRepository accountBookRepository;

    public AccountBookService() {
        accountBookRepository = new AccountBookRepository();
    }

    public String create(String accountName, int balance, int savingGoal, String memberIds) {
        return accountBookRepository.create(accountName, balance, savingGoal, memberIds);
    }

    public AccountBook findByName(String name) {
        List<AccountBook> accountBookList = accountBookRepository.findByAll();
        for (AccountBook accountBook : accountBookList) {
            if (accountBook.getAccountName().equals(name)) {
                return accountBook;
            }
        }
        return null;
    }

    public void select(AccountBook accountBook) {
        accountBookRepository.select(accountBook);
    }

    public void cancel() {
        accountBookRepository.cancel();
    }

    public void share(String shareId) {
        accountBookRepository.share(shareId);
    }

    public List<AccountBook> list() {
        return accountBookRepository.findByAll();
    }

    public void delete(AccountBook accountBook) {
        accountBookRepository.delete(accountBook);
    }

    public String modify(String modifyName, int balance, int savingGoal, String modifyDate, String accountName) {
        return accountBookRepository.modify(modifyName, balance, savingGoal, modifyDate, accountName);
    }

    public List<AccountBook> findByAll() {
        return accountBookRepository.findByAll();
    }
}
