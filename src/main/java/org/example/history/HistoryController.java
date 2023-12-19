package org.example.history;

import org.example.Global.Container;
import org.example.Request;

import java.sql.SQLException;
import java.util.List;

public class HistoryController {
    HistoryService historyService;

    Request request;

    public HistoryController() {
        historyService = new HistoryService();
    }

    public void command() {
        while (true) {
            System.out.println("=== 메인 → 5.가계부 → " + Container.getSeletedAccountBook().getAccountName() + " → 내역 ===");
            System.out.println("1.등록|2.보기(:월,일,항목,전체)|3.수정|4.삭제||5.뒤로");
            System.out.print("명령어 입력 : ");
            String command = Container.getSc().nextLine();
            request = new Request(command);
            switch (request.actionCode) {
                case "등록":
                    this.create();
                    break;
                case "보기":
                    this.read(request.readCommand);
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
        try {
            System.out.println("=== 메인 → 5.가계부 → " +
                    Container.getSeletedAccountBook().getAccountName() + " → 내역 → 1.등록 ===");
            System.out.print("날짜 입력 : ");
            String createDate = Container.getSc().nextLine().trim();
            System.out.println("항목 선택 : [1.고정비|2.식비|3.생활비|4.유흥비|5.교통비|6.교육비|7.금융비|8.세금|9.기타]");
            System.out.print("번호 입력 : ");
            int categoryId = Integer.parseInt(Container.getSc().nextLine().trim());
            if (categoryId < 1 || categoryId > 9) {
                System.out.println("해당 항목은 존재하지 않습니다.");
                return;
            }
            System.out.print("내용 : ");
            String content = Container.getSc().nextLine().trim();
            System.out.print("수입 : ");
            int income = Integer.parseInt(Container.getSc().nextLine().trim());
            System.out.print("지출 : ");
            int expense = Integer.parseInt(Container.getSc().nextLine().trim());
            int id = historyService.create(createDate, categoryId, content, income, expense);
            if (id == 0) return;
            System.out.println(createDate + "일자의 내역이 등록되었습니다.");
        } catch (NumberFormatException e) {
            System.out.println("숫자로 입력해주세요.");
        }
    }

    public void read(String command) {
        if (command == null) {
            System.out.println("=== 메인 → 5.가계부 → " +
                    Container.getSeletedAccountBook().getAccountName() + " → 내역 → 2.보기 ===");
            System.out.println("[1.월|2.일|3.항목|4.전체]");
            System.out.print("타입 선택 : ");
            command = Container.getSc().nextLine();
            switch (command) {
                case "1":
                    command = "월";
                    break;
                case "2":
                    command = "일";
                    break;
                case "3":
                    command = "항목";
                    break;
                case "4":
                    command = "전체";
                    break;
            }
        }
        switch (command) {
            case "1":
                command = "월";
                break;
            case "2":
                command = "일";
                break;
            case "3":
                command = "항목";
                break;
            case "4":
                command = "전체";
                break;
        }
        switch (command) {
            case "월":
                System.out.print("연 입력 : ");
                String yearM = Container.getSc().nextLine();
                System.out.print("월 입력 : ");
                String monthM = Container.getSc().nextLine();
                System.out.println("   날짜    |  항목  |  내용  |   수입   |   지출   ");
                List<HistoryDTO> historyDTOListM = historyService.viewByMonth(yearM, monthM);
                if (historyDTOListM == null) return;
                int sumIncomeM = historyService.sumIncome(yearM, monthM, "","");
                int sumExpenseM = historyService.sumExpense(yearM, monthM, "","");
                for (HistoryDTO historyDTO : historyDTOListM) {
                    System.out.println(historyDTO.getDate() + " | " +
                            historyDTO.getCategory() + " | " +
                            historyDTO.getContent() + " | " +
                            historyDTO.getIncome() + " | " +
                            historyDTO.getExpense());
                }
                System.out.println("  ● 총 수입 : +" + sumIncomeM + "    ● 총 지출 : -" + sumExpenseM + "\n");
                break;
            case "일":
                System.out.print("연 입력 : ");
                String yearD = Container.getSc().nextLine();
                System.out.print("월 입력 : ");
                String monthD = Container.getSc().nextLine();
                System.out.print("일 입력 : ");
                String dayD = Container.getSc().nextLine();
                System.out.println("   날짜    |  항목  |  내용  |   수입   |   지출   ");
                List<HistoryDTO> historyDTOListD = historyService.viewByDay(yearD, monthD, dayD);
                if (historyDTOListD == null) return;
                int sumIncomeD = historyService.sumIncome(yearD, monthD, dayD,"");
                int sumExpenseD = historyService.sumExpense(yearD, monthD, dayD,"");
                for (HistoryDTO historyDTO : historyDTOListD) {
                    System.out.println(historyDTO.getDate() + " | " +
                            historyDTO.getCategory() + " | " +
                            historyDTO.getContent() + " | " +
                            historyDTO.getIncome() + " | " +
                            historyDTO.getExpense());
                }
                System.out.println("  ● 총 수입 : +" + sumIncomeD + "    ● 총 지출 : -" + sumExpenseD + "\n");
                break;
            case "항목":
                System.out.println("[1.고정비|2.식비|3.생활비|4.유흥비|5.교통비|6.교육비|7.금융비|8.세금|9.기타]");
                System.out.print("항목 입력 : ");
                String categoryId = Container.getSc().nextLine();
                System.out.println("   날짜    |  항목  |  내용  |   수입   |   지출   ");
                List<HistoryDTO> historyDTOListC = historyService.viewByCategory(categoryId);
                if (historyDTOListC == null) return;
                int sumIncomeC = historyService.sumIncome("","","",categoryId);
                int sumExpenseC = historyService.sumExpense("","","",categoryId);
                for (HistoryDTO historyDTO : historyDTOListC) {
                    System.out.println(historyDTO.getDate() + " | " +
                            historyDTO.getCategory() + " | " +
                            historyDTO.getContent() + " | " +
                            historyDTO.getIncome() + " | " +
                            historyDTO.getExpense());
                }
                System.out.println("  ● 총 수입 : +" + sumIncomeC + "    ● 총 지출 : -" + sumExpenseC + "\n");
                break;
            case "전체":
                List<HistoryDTO> historyDTOListA = historyService.viewByAll();
                if (historyDTOListA == null) return;
                int sumIncomeA = historyService.sumIncome("","","","");
                int sumExpenseA = historyService.sumExpense("","","","");
                for (HistoryDTO historyDTO : historyDTOListA) {
                    System.out.println(historyDTO.getDate() + " | " +
                            historyDTO.getCategory() + " | " +
                            historyDTO.getContent() + " | " +
                            historyDTO.getIncome() + " | " +
                            historyDTO.getExpense());
                }
                System.out.println("  ● 총 수입 : +" + sumIncomeA + "    ● 총 지출 : -" + sumExpenseA + "\n");
                break;
        }
    }

    public void delete() {

    }

    public void update() {

    }
}
