package org.example.wiseSaying;

import org.example.Global.Container;

import java.util.List;
import java.util.Random;

public class WiseSayingController {
    String command;
    WiseSayingService wiseSayingService;

    public WiseSayingController() {
        wiseSayingService = new WiseSayingService();
    }

    public void command() {
        if (Container.getLoggedInMember()==null){
            System.out.println("권한이 없습니다.");
            return;
        }
        if (!Container.getLoggedInMember().isManager()) {
            System.out.println("권한이 없습니다.");
            return;
        }
        while (true) {
            System.out.println("\n1.생성|2.목록|3.삭제|4.수정|5.뒤로");
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
                    command = "삭제";
                    break;
                case "4":
                    command = "수정";
                    break;
                case "5":
                    command = "뒤로";
                    return;
            }
            switch (command) {
                case "생성":
                    this.create();
                    break;
                case "목록":
                    this.list();
                    break;
                case "수정":
                    this.update();
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
        System.out.print("명언 등록 : ");
        String wiseSaying = Container.getSc().nextLine();
        wiseSayingService.create(wiseSaying);
        System.out.println("등록 완료");
    }

    public void list() {
        List<WiseSaying> wiseSayingList = wiseSayingService.list();
        for (WiseSaying wiseSaying : wiseSayingList) {
            System.out.println(wiseSaying.getWiseSayingId() + ". " + wiseSaying.getWiseSaying());
        }
    }

    public void delete() {
        this.list();
        System.out.print("번호 입력 : ");
        int deleteId = Integer.parseInt(Container.getSc().nextLine());
        wiseSayingService.delete(deleteId);
    }

    public void update() {
        this.list();
        System.out.print("번호 입력 : ");
        int updateId = Integer.parseInt(Container.getSc().nextLine());
        System.out.println("기존 명언 : " + wiseSayingService.findByWiseSaying(updateId));
        System.out.print("명언 변경 : ");
        String updateWiseSaying = Container.getSc().nextLine();
        wiseSayingService.update(updateWiseSaying , updateId);
    }
    public void setWiseSaying() {
        List<WiseSaying> wiseSayingList = wiseSayingService.findByAll();
        if (wiseSayingList == null || wiseSayingList.isEmpty()) {
            return;
        }
        Random random = new Random();
        int randomNum = random.nextInt(wiseSayingList.size());
        Container.setWiseSaying(wiseSayingList.get(randomNum));
    }
}
