package org.example.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
public class Member {
    private int id;
    private String userName;
    private String password;
    private String nickname;
    private boolean manager;
    private String regDate;
    private String modifyDate;

    Member(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.userName = (String) row.get("userName");
        this.password = (String) row.get("password");
        this.nickname = (String) row.get("nickname");
        this.manager = (boolean) row.get("manager");
        this.regDate = row.get("regDate").toString();
        this.modifyDate = row.get("modifyDate").toString();
    }

}
