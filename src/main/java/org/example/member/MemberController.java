package org.example.member;

import org.example.Global.Container;

import java.util.ArrayList;
import java.util.List;

public class MemberController {

    MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    public void signUp() {
        System.out.println("가입을 환영합니다.");
        String userName;
        String password;
        String nickname;
        while (true) {
            System.out.print("ID : ");
            String checkName = Container.getSc().nextLine().trim();
            boolean check = memberService.findBySameId(checkName);
            if (check) {
                System.out.println("중복된 ID가 있습니다.");
            }else {
                userName = checkName;
                break;
            }
        }
        while (true) {
            System.out.print("PASSWORD : ");
            String passwordCheck1 = Container.getSc().nextLine().trim();
            System.out.print("PASSWORD확인 : ");
            String passwordCheck2 = Container.getSc().nextLine().trim();
            if (!passwordCheck1.equals(passwordCheck2)) {
                System.out.println();
                password = passwordCheck1;
                break;
            }
            System.out.println("비밀번호를 확인하고 다시 입력하세요");
        }
        while (true) {
            System.out.print("닉네임 : ");
            String nicknameCheck = Container.getSc().nextLine().trim();
            if (nicknameCheck.length() <= 8) {
                nickname = nicknameCheck;
                break;
            }
        }
        String signUpName = memberService.signUp(userName, password, nickname);
        memberService.login();
        System.out.println(signUpName + "님 가입을 환영합니다.");
    }

    public void login() {

    }

    public void logout() {

    }

    public void withdrawal() {

    }
}


