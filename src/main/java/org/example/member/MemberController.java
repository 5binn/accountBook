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
            Member check = memberService.findBySameId(checkName);
            if (check != null) {
                System.out.println("중복된 ID가 있습니다.");
            } else {
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
                System.out.println("비밀번호를 확인하고 다시 입력하세요");
            } else {
                password = passwordCheck1;
                break;
            }
        }
        while (true) {
            System.out.print("닉네임 : ");
            String nicknameCheck = Container.getSc().nextLine().trim();
            if (nicknameCheck.length() > 8) {
                System.out.println("닉네임은 8글자까지 가능합니다.");
            } else {
                nickname = nicknameCheck;
                break;
            }
        }
        String signUpName = memberService.signUp(userName, password, nickname);
        System.out.println(signUpName + "님 가입을 환영합니다.");
    }

    public void login() {
        if (Container.getLoggedInMember() != null) {
            System.out.println(Container.getLoggedInMember().getUserName() + "님이 로그인 되어있습니다.");
            return;
        }

        System.out.print("ID : ");
        String checkName = Container.getSc().nextLine().trim();
        System.out.print("PASSWORD : ");
        String checkPassword = Container.getSc().nextLine().trim();

        Member check = memberService.findBySameId(checkName);
        if (check == null) {
            System.out.println("해당 아이디는 존재하지 않습니다.");
            return;
        }
        if (!check.getPassword().equals(checkPassword)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }
        memberService.login(check);
        System.out.println(Container.getLoggedInMember().getUserName() + " 님이 로그인 되었습니다.");
    }

    public void logout() {
        if (Container.getLoggedInMember() == null) {
            System.out.println("현재 사용자가 없습니다. 로그인을 먼저 해주세요.");
            return;
        }
        System.out.println(Container.getLoggedInMember().getUserName() + " 님이 로그아웃 되었습니다.");
        memberService.logout();
    }

    public void withdrawal() {
        if (Container.getLoggedInMember() == null) {
            System.out.println("현재 사용자가 없습니다. 로그인을 먼저 해주세요.");
            return;
        }
        System.out.println(Container.getLoggedInMember().getUserName() + "님의 탈퇴를 진행합니다.");
        while (true) {
            System.out.print("탈퇴하시겠습니까?(Y/N) : ");
            String response = Container.getSc().nextLine();
            if (response.equals("Y") || response.equals("y")) {
                memberService.withdrawal();
            } else if (response.equals("N") || response.equals("n")) {
                return;
            } else {
                System.out.println("Y/N 또는 y/n 만 입력 가능합니다.");
            }
        }
    }
}


