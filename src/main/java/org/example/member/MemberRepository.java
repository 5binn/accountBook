package org.example.member;

import org.example.Global.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {

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
        return memberList.get(memberList.size()-1).getUserName();
    }
    public void login(Member member) {
        Container.setLoggedInMember(member);
    }

    public void logout() {
        Container.setLoggedInMember(null);
        Container.setSeletedAccountBook(null);
    }

    public void withdrawal() {
        String sql = String.format("DELETE FROM `member` WHERE userName = '%s';", Container.getLoggedInMember().getUserName());
        Container.getDbConnection().delete(sql);
    }
    public List<Member> findByAll() {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM `member`";
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            memberList.add(member);
        }
        return memberList;
    }

}
