package org.example.accountBook;

import org.example.Global.Container;
import org.example.history.HistoryController;
import org.example.history.HistoryService;
import org.example.member.Member;
import org.example.member.MemberService;

import java.util.List;

public class AccountBookController {
    String command;
    private final AccountBookService accountBookService;
    private final MemberService memberService;
    private final HistoryController historyController;
    private final HistoryService historyService;

    public AccountBookController() {
        accountBookService = new AccountBookService();
        memberService = new MemberService();
        historyController = new HistoryController();
        historyService = new HistoryService();
    }

    public void command() {
        if (Container.getLoggedInMember() == null) {
            System.out.println("현재 사용자가 없습니다. 로그인을 먼저 해주세요.");
            return;
        }
        while (true) {
            System.out.println("\n=== 메인 → 5.가계부 ===");
            System.out.println("1.생성|2.목록|3.선택|4.취소|5.뒤로");
            System.out.print("명령어 입력 : ");
            this.command = Container.getSc().nextLine();
            switch (command) {
                case "1":
                    command = "생성";
                    break;
                case "2":
                    command = "목록";
                    break;
                case "3":
                    command = "선택";
                    break;
                case "4":
                    command = "취소";
                    break;
                case "5":
                    return;
            }
            switch (command) {
                case "생성":
                    this.create();
                    break;
                case "목록":
                    this.list();
                    break;
                case "선택":
                    this.select();
                    break;
                case "취소":
                    this.cancel();
                    break;
                case "뒤로":
                    return;
            }
        }


    }

    public void create() {
        System.out.println("\n=== 메인 → 5.가계부 → 1.생성 ===");
        if (!this.loginCheck()) return;
        System.out.println("가계부를 생성합니다.");
        System.out.print("가계부 이름 : ");
        String name = Container.getSc().nextLine();
        AccountBook accountBook = accountBookService.findByName(name);
        if (accountBook != null) {
            System.out.println("같은 이름의 가계부가 존재합니다.");
            return;
        }
        System.out.print("잔액 입력 : ");
        int balance = Integer.parseInt(Container.getSc().nextLine());
        System.out.print("목표금액 입력 : ");
        int savingGoal = Integer.parseInt(Container.getSc().nextLine());

        String userId = accountBookService.create(name, balance, savingGoal, Container.getLoggedInMember().getUserName());
        System.out.println(userId + "님의 가계부 " + name + "가 등록되었습니다.");
    }

    public void select() {
        System.out.println("\n=== 메인 → 5.가계부 → 3.선택 ===");
        if (!this.loginCheck()) return;
        this.list();
        System.out.println("사용할 가계부의 이름을 입력해주세요.");
        System.out.print("이름 입력 : ");
        String name = Container.getSc().nextLine();
        if (Container.getSeletedAccountBook() != null) {
            if (Container.getSeletedAccountBook().getAccountName().equals(name)) {
                System.out.println("현재 사용중인 가계부입니다.");
                return;
            }
        }
        AccountBook accountBook = accountBookService.findByName(name);
        if (accountBook == null) {
            System.out.println("해당 이름의 가계부가 존재하지 않습니다.");
            return;
        }
        accountBookService.select(accountBook);
        while (true) {
            System.out.println("\n=== 메인 → 5.가계부 → " + Container.getSeletedAccountBook().getAccountName() + " ===");
            System.out.println("1.공유|2.수정|3.삭제|4.내역|5.뒤로");
            System.out.print("명령어 입력 : ");
            this.command = Container.getSc().nextLine();
            switch (command) {
                case "1":
                    command = "공유";
                    break;
                case "2":
                    command = "수정";
                    break;
                case "3":
                    command = "삭제";
                    break;
                case "4":
                    command = "내역";
                    break;
                case "5":
                    return;
            }
            switch (command) {
                case "공유":
                    this.share();
                    break;
                case "수정":
                    this.modify();
                    break;
                case "삭제":
                    this.delete();
                    break;
                case "내역":
                    historyController.command();
                    return;
                case "뒤로":
                    return;
            }
        }
    }

    public void cancel() {
        if (!this.loginCheck()) return;
        if (Container.getSeletedAccountBook() == null) {
            System.out.println("현재 선택하신 가계부가 없습니다.");
            return;
        }
        System.out.println("=== 메인 → " + Container.getSeletedAccountBook().getAccountName() + " → 4.취소 ===");
        accountBookService.cancel();
    }

    public void share() {
        if (!this.loginCheck()) return;
        if (Container.getSeletedAccountBook() == null) {
            System.out.println("현재 선택하신 가계부가 없습니다.");
            return;
        }
        System.out.println("\n=== 메인 → 5.가계부 → " + Container.getSeletedAccountBook().getAccountName() + " → 5.공유 ===");
        System.out.println("공유하실 상대방 ID를 입력해주세요.");
        System.out.print("상대 ID : ");
        String shareId = Container.getSc().nextLine();
        Member member = memberService.findBySameId(shareId);
        if (member == null) {
            System.out.println("해당 ID의 회원이 존재하지 않습니다.");
            return;
        }
        accountBookService.share(shareId);
        System.out.println(Container.getLoggedInMember().getUserName() + "님의 " +
                Container.getSeletedAccountBook().getAccountName() + "(이)가 " + shareId + "님과 공유되었습니다.");
    }

    public void list() {
        System.out.println("\n=== 메인 → 5.가계부 → 2.목록 ===");
        if (!this.loginCheck()) return;

        System.out.println(Container.getLoggedInMember().getUserName() + " 님의 가계부 목록입니다.");
        System.out.println("  이름   |      잔액      |        목표\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        List<AccountBook> accountBookList = accountBookService.list();
        for (AccountBook accountBook : accountBookList) {
            this.accountBookService.select(accountBook);
            int sumIncomeA = historyService.sumIncome("", "", "", "");
            int sumExpenseA = historyService.sumExpense("", "", "", "");
            String nameFormat = accountBook.getAccountName() + "     ";
            if (accountBook.getAccountName().length() == 2) {
                nameFormat = nameFormat.substring(0, 5) + "  ";
            } else if (accountBook.getAccountName().length() == 3) {
                nameFormat = nameFormat.substring(0, 5) + " ";
            } else if (accountBook.getAccountName().length() == 4) {
                nameFormat = nameFormat.substring(0, 5);
            } else if (accountBook.getAccountName().isEmpty()) {
                nameFormat = "         ";
            } else if (accountBook.getAccountName().length() == 1) {
                nameFormat = nameFormat.substring(0, 5) + "   ";
            }
            String attainment = "[ ]";
            if (accountBook.balance + sumIncomeA - sumExpenseA >= accountBook.savingGoal) {
                attainment = "[✔]";
            }
            System.out.println(nameFormat + " | " + String.format("%,11d", accountBook.balance + sumIncomeA - sumExpenseA) + " ￦ | " + String.format("%,11d", accountBook.savingGoal) + " ￦ " + attainment);
            this.accountBookService.cancel();
        }
    }

    public void delete() {
        System.out.println("\n=== 메인 → 5.가계부 → " + Container.getSeletedAccountBook().getAccountName() + " 7.삭제 ===");
        if (!this.loginCheck()) return;

        while (true) {
            System.out.print("가계부 " +Container.getSeletedAccountBook().getAccountName() + "(을)를 삭제하시겠습니까?(Y/N) : ");
            String response = Container.getSc().nextLine();
            if (response.equals("Y") || response.equals("y")) {
                historyService.deleteAll(Container.getSeletedAccountBook().getId());
                System.out.println("가계부 " + Container.getSeletedAccountBook().getAccountName() + " (이)가 삭제되었습니다.");
                accountBookService.delete(Container.getSeletedAccountBook());
            }
            else if (response.equals("N") || response.equals("n")) {
                return;
            } else {
                System.out.println("Y/N 또는 y/n 만 입력 가능합니다.");
            }
        }
    }

    public void modify() {
        System.out.println("\n=== 메인 → 5.가계부 → 6.수정 ===");
        if (!this.loginCheck()) return;
        System.out.println("수정할 가계부의 이름을 입력해주세요.");
        System.out.print("입력 : ");
        String accountName = Container.getSc().nextLine();
        AccountBook accountBook = accountBookService.findByName(accountName);
        if (accountBook == null) {
            System.out.println("해당 이름의 가계부가 존재하지 않습니다.");
            return;
        }

        System.out.println("기존 이름 : " + accountBook.getAccountName());
        System.out.print("이름 입력 : ");
        String nameCheck = Container.getSc().nextLine();
        AccountBook accountCheck = accountBookService.findByName(nameCheck);
        if (accountCheck != null) {
            System.out.println("같은 이름의 가계부가 존재합니다.");
            return;
        }
        System.out.println("기존 잔액 : " + accountBook.getBalance());
        System.out.print("잔액 입력: ");
        int balance = Integer.parseInt(Container.getSc().nextLine());
        System.out.println("기존 목표 : " + accountBook.getSavingGoal());
        System.out.print("목표 입력 : ");
        int savingGoal = Integer.parseInt(Container.getSc().nextLine());

        String name = accountBookService.modify(nameCheck, balance, savingGoal, accountBook.getRegDate(), accountName);
        System.out.println(Container.getLoggedInMember().getUserName() + "님의 " + name + "->" + nameCheck + " 로 수정되었습니다.");
    }


    public boolean loginCheck() {
        if (Container.getLoggedInMember() == null) {
            System.out.println("먼저 로그인을 해주세요.");
            return false;
        }
        return true;
    }

    /*public boolean idCheck() {
        return Container.getSeletedAccountBook().getMemberId() == Container.getLoggedInMember().getId();
    }*/


}

