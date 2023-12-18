package org.example;

import org.example.DB.DBConnection;
import org.example.Global.Container;
import org.example.accountBook.AccountBookController;
import org.example.member.MemberController;
import org.example.wiseSaying.WiseSaying;
import org.example.wiseSaying.WiseSayingController;

public class App {
    MemberController memberController;

    AccountBookController accountBookController;

    WiseSayingController wiseSayingController;
    WiseSaying wiseSaying;

    App() {
        DBConnection.DB_NAME = "accountBook";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDbConnection().connect();

        memberController = new MemberController();
        accountBookController = new AccountBookController();
        wiseSayingController = new WiseSayingController();
        wiseSayingController.setWiseSaying();
        try {
            wiseSaying = Container.getWiseSayings();
        }catch (NullPointerException e) {
            System.out.println("등록된 명언이 없습니다.");
        }
    }

    public void run() {
        System.out.println("== 너의 통장을 생각해라 ==\n" + wiseSaying.getWiseSaying());
        while (true) {
            System.out.println("=== 메인 ===\n1.가입|2.로그인|3.로그아웃|4.탈퇴|5.가계부|6.종료");
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
                case "명언":
                    wiseSayingController.command();
                    break;
            }
        }
    }
}
