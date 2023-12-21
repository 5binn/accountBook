package org.example.member;

import org.example.Global.Container;
import org.example.accountBook.AccountBook;
import org.example.accountBook.AccountBookService;

import java.util.List;


public class MemberController {

    private final MemberService memberService;
    private final AccountBookService accountBookService;

    public MemberController() {
        memberService = new MemberService();
        accountBookService = new AccountBookService();
    }

    public void signUp() {
        System.out.println("\n=== 메인 → 1.가입 ===");
        String userName;
        String password;
        String nickname;
        while (true) {
            System.out.print("ID : ");
            String checkName = Container.getSc().nextLine().trim();
            Member check = memberService.findBySameId(checkName);
            if (checkName.length() > 8) {
                System.out.println("ID는 8글자까지 가능합니다.");
            }
            else if (check != null) {
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
            if (nicknameCheck.length() > 6) {
                System.out.println("닉네임은 6글자까지 가능합니다.");
            } else {
                nickname = nicknameCheck;
                break;
            }
        }
        String signUpName = memberService.signUp(userName, password, nickname);
        System.out.println(signUpName + "님 가입을 환영합니다.");
    }

    public void login() {
        System.out.println("\n=== 메인 → 2.로그인 ===");
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
        System.out.println(Container.getLoggedInMember().getUserName() + " 님 환영합니다.");
    }

    public void logout() {
        System.out.println("\n=== 메인 → 3.로그아웃 ===");
        if (Container.getLoggedInMember() == null) {
            System.out.println("현재 사용자가 없습니다. 로그인을 먼저 해주세요.");
            return;
        }
        System.out.println(Container.getLoggedInMember().getUserName() + " 님이 로그아웃 되었습니다.");
        memberService.logout();
    }

    public void withdrawal() {
        System.out.println("\n=== 메인 → 4.탈퇴 ===");
        if (Container.getLoggedInMember() == null) {
            System.out.println("현재 사용자가 없습니다. 로그인을 먼저 해주세요.");
            return;
        }
        System.out.println(Container.getLoggedInMember().getUserName() + "님의 탈퇴를 진행합니다.");
        while (true) {
            System.out.print("탈퇴하시겠습니까?(Y/N) : ");
            String response = Container.getSc().nextLine();
            if (response.equals("Y") || response.equals("y")) {
                List<AccountBook> accountBookList = accountBookService.findByAll();
                for (AccountBook accountBook : accountBookList) {
                    if (accountBook.getMemberId() == Container.getLoggedInMember().getId()) {
                        accountBookService.delete(accountBook);
                    }
                }
                memberService.withdrawal();
                memberService.logout();
                System.out.println("탈퇴처리가 완료되었습니다.");
                return;
            } else if (response.equals("N") || response.equals("n")) {
                return;
            } else {
                System.out.println("Y/N 또는 y/n 만 입력 가능합니다.");
            }
        }
    }
}


