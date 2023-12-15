package org.example;

import org.example.DB.DBConnection;
import org.example.Global.Container;
import org.example.member.MemberController;

public class App {
    MemberController memberController;

    App() {
        DBConnection.DB_NAME = "accountBook";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDbConnection().connect();

        memberController = new MemberController();
    }

    public void run() {
        System.out.println("시스템 시작");
        while (true) {
            System.out.print("명령어 입력 : ");
            String command = Container.getSc().nextLine().trim();
            switch (command) {
                case "종료":
                    return;
                case "가입":
                    memberController.signUp();
                    break;
                case "로그인":
                    memberController.login();
                    break;
                case "로그아웃":
                    memberController.logout();
                    break;
                case "탈퇴":
                    memberController.withdrawal();
                    break;
                case "가계부생성":
                    break;
                case "가계부목록":
                    break;
                case "가계부기입":
                    break;
                case "가계부삭제":
                    break;
            }
        }
    }
}
