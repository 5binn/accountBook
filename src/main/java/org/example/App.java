package org.example;

import org.example.DB.DBConnection;
import org.example.Global.Container;
import org.example.accountBook.AccountBookController;
import org.example.member.MemberController;

public class App {
    MemberController memberController;

    AccountBookController accountBookController;

    App() {
        DBConnection.DB_NAME = "accountBook";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDbConnection().connect();

        memberController = new MemberController();
        accountBookController = new AccountBookController();
    }

    public void run() {
        System.out.println("시스템 시작");
        while (true) {
            System.out.println("1.가입|2.로그인|3.로그아웃|4.탈퇴|5.가계부|6.종료");
            System.out.print("명령어 입력 : ");
            String command = Container.getSc().nextLine().trim();
            switch (command) {
                case "1":
                    command = "가입";
                    break;
                case "2":
                    command = "로그인";
                    break;
                case "3":
                    command = "로그아웃";
                    break;
                case "4":
                    command = "탈퇴";
                    break;
                case "5":
                    command = "가계부";
                    break;
                case "6":
                    return;
            }
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
                case "가계부":
                    accountBookController.command();
                    break;
                case "가계부생성":
                    accountBookController.create();
                    break;
                case "가계부선택":
                    accountBookController.select();
                    break;
                case "가계부선택취소":
                    accountBookController.cancel();
                    break;
                case "가계부목록":
                    accountBookController.list();
                    break;
                case "가계부수정":
                    accountBookController.modify();
                    break;
                case "가계부삭제":
                    accountBookController.delete();
                    break;
            }
        }
    }
}
