package org.example.member;

import org.example.Global.Container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    int lastMemberId = 1;


    public String signUp(String userName, String password, String nickname) {
        String sql = String.format("INSERT INTO `member`" +
                                    "SET userName = '%s'," +
                                    "`password` = '%s'," +
                                    "`nickname` = '%s'," +
                                    "`manager` = 0," +
                                    "regDate = now()," +
                                    "modifyDate = now();", userName, password, nickname);
        int id = Container.getDbConnection().insert(sql);
        List<Member> memberList = this.findByAll();
        return memberList.get(id).getUserName();
    }
    public void login() {

    }

    public void logout() {

    }

    public void withdrawal() {

    }
    public List<Member> findByAll() {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELET * FROM `member`";
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            memberList.add(member);
        }
        return memberList;
    }

}
