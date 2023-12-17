package org.example.accountBook;

import org.example.Global.Container;

import java.util.List;

public class AccountBookController {
    private AccountBookService accountBookService;

    public AccountBookController() {
        accountBookService = new AccountBookService();
    }

    String command;

    public void command() {
        if (Container.getLoggedInMember() == null) {
            System.out.println("현재 사용자가 없습니다. 로그인을 먼저 해주세요.");
            return;
        }
        System.out.println("1.생성|2.목록|3.선택|4.취소|5.수정|6.삭제|7.뒤로");
        while (true) {
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
                    command = "수정";
                    break;
                case "6":
                    command = "삭제";
                    break;
                case "7":
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
                case "수정":
                    this.modify();
                    break;
                case "삭제":
                    this.delete();
                    break;
                case "뒤로":
                    return;
            }
        }

    }

    public void create() {
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
        if (!this.loginCheck()) return;
        System.out.println("사용할 가계부의 이름을 입력해주세요.");
        System.out.print("입력 : ");
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
    }

    public void cancel() {
        if (!this.loginCheck()) return;
        if (Container.getSeletedAccountBook() == null) {
            System.out.println("현재 사용중인 가계부가 없습니다.");
            return;
        }
        accountBookService.cancel();
    }

    public void list() {
        if (!this.loginCheck()) return;
        System.out.println(Container.getLoggedInMember().getUserName() + " 님의 가계부 목록입니다.");
        System.out.println("이름 | 잔액 | 목표\n----------------------");
        List<AccountBook> accountBookList = accountBookService.list();
        for (AccountBook accountBook : accountBookList) {
            System.out.println(accountBook.accountName + " | " + accountBook.balance + "원 | " + accountBook.savingGoal + "원");
        }
    }

    public void delete() {
        if (!this.loginCheck()) return;
        System.out.println("삭제할 가계부의 이름을 입력해주세요.");
        System.out.print("입력 : ");
        String deleteName = Container.getSc().nextLine();
        AccountBook accountBook = accountBookService.findByName(deleteName);
        if (accountBook == null) {
            System.out.println("해당 이름의 가계부가 존재하지 않습니다.");
            return;
        }
        System.out.println("가계부 " + accountBook.accountName + " (이)가 삭제되었습니다.");
        accountBookService.delete(accountBook);
    }

    public void modify() {
        AccountBook accountBook;
        if (!this.loginCheck()) return;
        System.out.println("수정할 가계부의 이름을 입력해주세요.");
        System.out.print("입력 : ");
        String modifyName = Container.getSc().nextLine();
        accountBook = accountBookService.findByName(modifyName);
        if (accountBook == null) {
            System.out.println("해당 이름의 가계부가 존재하지 않습니다.");
            return;
        }
        while (true) {
            System.out.println("기존 이름 : " + accountBook.getAccountName());
            System.out.print("이름 입력 : ");
            String name = Container.getSc().nextLine();
            accountBook = accountBookService.findByName(name);
            if (accountBook != null) {
                System.out.println("같은 이름의 가계부가 존재합니다.");
                continue;
            }
            modifyName = name;
            break;
        }

        System.out.println("기존 잔액 : " + accountBook.getBalance());
        System.out.print("잔액 입력: ");
        int balance = Integer.parseInt(Container.getSc().nextLine());
        System.out.println("기존 목표 : " + accountBook.getSavingGoal());
        System.out.print("목표 입력 : ");
        int savingGoal = Integer.parseInt(Container.getSc().nextLine());

        String name = accountBookService.modify(modifyName, balance, savingGoal, accountBook.getRegDate());
        System.out.println("가계부 " + name + " (이)가 수정되었습니다.");
    }


    public boolean loginCheck() {
        if (Container.getLoggedInMember() == null) {
            System.out.println("먼저 로그인을 해주세요.");
            return false;
        }
        return true;
    }


}

