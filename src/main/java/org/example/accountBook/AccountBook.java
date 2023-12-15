package org.example.accountBook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@AllArgsConstructor
@Setter
@Getter
public class AccountBook {
    int id;
    int memberId;
    String accountName;
    int balance;
    int savingGoal;
    String memberIds;
    String regDate;
    String modifyDate;

    AccountBook(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.memberId = (int) row.get("memberId");
        this.accountName = (String) row.get("accountName");
        this.balance = (int) row.get("balance");
        this.savingGoal = (int) row.get("savingGoal");
        this.memberIds = (String) row.get("memberIds");
        this.regDate = row.get("regDate").toString();
        this.modifyDate = row.get("modifyDate").toString();
    }
}
