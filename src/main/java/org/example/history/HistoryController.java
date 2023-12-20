package org.example.history;

import org.example.Global.Container;
import org.example.Request;

import java.util.List;

public class HistoryController {
    private final HistoryService historyService;

    Request request;


    public HistoryController() {
        historyService = new HistoryService();
    }

    public void command() {
        while (true) {
            System.out.println("\n=== 메인 → 5.가계부 → " + Container.getSeletedAccountBook().getAccountName() + " → 4.내역 ===");
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
            System.out.println("\n=== 메인 → 5.가계부 → " +
                    Container.getSeletedAccountBook().getAccountName() + " → 4.내역 → 1.등록 ===");
            System.out.print("날짜 입력(YYMMDD|YY.MM.DD|YY-MM-DD) : ");
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
        Container.numFormat();
        if (command == null) {
            System.out.println("\n=== 메인 → 5.가계부 → " +
                    Container.getSeletedAccountBook().getAccountName() + " → 4.내역 → 2.보기 ===");
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
                System.out.print("연도 입력(YYYY|YY) : ");
                String yearM = Container.getSc().nextLine();
                if (yearM.length() < 5) {
                    yearM = String.format("20%s", yearM);
                }
                System.out.print("월 입력(MM) : ");
                String monthM = Container.getSc().nextLine();
                if (monthM.length() == 1) {
                    monthM = String.format("0%s", monthM);
                }
                System.out.println("    날짜   |  항목  |   내용    |      수입      |       지출    \nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                List<HistoryDTO> historyDTOListM = historyService.viewByMonth(yearM, monthM);
                if (historyDTOListM == null) return;
                int sumIncomeM = historyService.sumIncome(yearM, monthM, "", "");
                int sumExpenseM = historyService.sumExpense(yearM, monthM, "", "");
                for (HistoryDTO historyDTO : historyDTOListM) {
                    String categoryFormat = historyDTO.getCategory();
                    if (historyDTO.getCategory().length() == 2) {
                        categoryFormat = String.format(" %s ", historyDTO.getCategory());
                    }
                    String contentFormat = historyDTO.getContent();
                    contentFormat += "         ";
                    if (historyDTO.getContent().length() == 2) {
                        contentFormat = contentFormat.substring(0, 5) + "  ";
                    } else if (historyDTO.getContent().length() == 3) {
                        contentFormat = contentFormat.substring(0, 5) + " ";
                    } else if (historyDTO.getContent().length() == 4) {
                        contentFormat = contentFormat.substring(0, 5);
                    } else if (historyDTO.getContent().isEmpty()) {
                        contentFormat = "         ";
                    }
                    System.out.println(historyDTO.getDate() + " | " +
                            String.format("%s", categoryFormat) + " | " +
                            String.format("%s", contentFormat) + " | " +
                            String.format("%,11d", historyDTO.getIncome()) + " ￦ | " +
                            String.format("%,11d", historyDTO.getExpense()) + " ￦");
                }
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" +
                        "\n                      ● 총 수입 : +" + sumIncomeM + " ￦ ● 총 지출 : -" + sumExpenseM + " ￦\n");
                break;
            case "일":
                System.out.print("연도 입력(YYYY|YY) : ");
                String yearD = Container.getSc().nextLine();
                if (yearD.length() < 5) {
                    yearD = String.format("20%s", yearD);
                }
                System.out.print("월 입력(MM|M) : ");
                String monthD = Container.getSc().nextLine();
                if (monthD.length() == 1) {
                    monthD = String.format("0%s", monthD);
                }
                System.out.print("일 입력(DD|D) : ");
                String dayD = Container.getSc().nextLine();
                if (dayD.length() == 1) {
                    dayD = String.format("0%s", dayD);
                }
                System.out.println("    날짜   |  항목  |   내용    |      수입      |       지출    \nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                List<HistoryDTO> historyDTOListD = historyService.viewByDay(yearD, monthD, dayD);
                if (historyDTOListD == null) return;
                int sumIncomeD = historyService.sumIncome(yearD, monthD, dayD, "");
                int sumExpenseD = historyService.sumExpense(yearD, monthD, dayD, "");
                for (HistoryDTO historyDTO : historyDTOListD) {
                    String categoryFormat = historyDTO.getCategory();
                    if (historyDTO.getCategory().length() == 2) {
                        categoryFormat = String.format(" %s ", historyDTO.getCategory());
                    }
                    String contentFormat = historyDTO.getContent();
                    contentFormat += "         ";
                    if (historyDTO.getContent().length() == 2) {
                        contentFormat = contentFormat.substring(0, 5) + "  ";
                    } else if (historyDTO.getContent().length() == 3) {
                        contentFormat = contentFormat.substring(0, 5) + " ";
                    } else if (historyDTO.getContent().length() == 4) {
                        contentFormat = contentFormat.substring(0, 5);
                    } else if (historyDTO.getContent().isEmpty()) {
                        contentFormat = "         ";
                    }
                    System.out.println(historyDTO.getDate() + " | " +
                            String.format("%s", categoryFormat) + " | " +
                            String.format("%s", contentFormat) + " | " +
                            String.format("%,11d", historyDTO.getIncome()) + " ￦ | " +
                            String.format("%,11d", historyDTO.getExpense()) + " ￦");
                }
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" +
                        "\n                      ● 총 수입 : +" + sumIncomeD + " ￦ ● 총 지출 : -" + sumExpenseD + " ￦\n");
                break;
            case "항목":
                System.out.println("[1.고정비|2.식비|3.생활비|4.유흥비|5.교통비|6.교육비|7.금융비|8.세금|9.기타]");
                System.out.print("항목 입력 : ");
                String categoryId = Container.getSc().nextLine();
                System.out.println("    날짜   |  항목  |   내용    |      수입      |       지출    \nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                List<HistoryDTO> historyDTOListC = historyService.viewByCategory(categoryId);
                if (historyDTOListC == null) return;
                int sumIncomeC = historyService.sumIncome("", "", "", categoryId);
                int sumExpenseC = historyService.sumExpense("", "", "", categoryId);
                for (HistoryDTO historyDTO : historyDTOListC) {
                    String categoryFormat = historyDTO.getCategory();
                    if (historyDTO.getCategory().length() == 2) {
                        categoryFormat = String.format(" %s ", historyDTO.getCategory());
                    }
                    String contentFormat = historyDTO.getContent();
                    contentFormat += "         ";
                    if (historyDTO.getContent().length() == 2) {
                        contentFormat = contentFormat.substring(0, 5) + "  ";
                    } else if (historyDTO.getContent().length() == 3) {
                        contentFormat = contentFormat.substring(0, 5) + " ";
                    } else if (historyDTO.getContent().length() == 4) {
                        contentFormat = contentFormat.substring(0, 5);
                    } else if (historyDTO.getContent().isEmpty()) {
                        contentFormat = "         ";
                    }
                    System.out.println(historyDTO.getDate() + " | " +
                            String.format("%s", categoryFormat) + " | " +
                            String.format("%s", contentFormat) + " | " +
                            String.format("%,11d", historyDTO.getIncome()) + " ￦ | " +
                            String.format("%,11d", historyDTO.getExpense()) + " ￦");
                }

                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" +
                        "\n                              ● 총 수입 : +" + sumIncomeC + " ￦ ● 총 지출 : -" + sumExpenseC + " ￦\n");
                break;
            case "전체":
                this.readAll();
                break;
        }
    }
    public void readAll() {
        System.out.println("    날짜   |  항목  |   내용    |      수입      |       지출    \nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        List<HistoryDTO> historyDTOListA = historyService.viewByAll();
        if (historyDTOListA == null) return;

        int sumIncomeA = historyService.sumIncome("", "", "", "");
        int sumExpenseA = historyService.sumExpense("", "", "", "");
        for (HistoryDTO historyDTO : historyDTOListA) {
            String categoryFormat = historyDTO.getCategory();
            if (historyDTO.getCategory().length() == 2) {
                categoryFormat = String.format(" %s ", historyDTO.getCategory());
            }
            String contentFormat = historyDTO.getContent();
            contentFormat += "         ";
            if (historyDTO.getContent().length() == 2) {
                contentFormat = contentFormat.substring(0, 5) + "  ";
            } else if (historyDTO.getContent().length() == 3) {
                contentFormat = contentFormat.substring(0, 5) + " ";
            } else if (historyDTO.getContent().length() == 4) {
                contentFormat = contentFormat.substring(0, 5);
            } else if (historyDTO.getContent().isEmpty()) {
                contentFormat = "         ";
            }
            System.out.println(historyDTO.getDate() + " | " +
                    String.format("%s", categoryFormat) + " | " +
                    String.format("%s", contentFormat) + " | " +
                    String.format("%,11d", historyDTO.getIncome()) + " ￦ | " +
                    String.format("%,11d", historyDTO.getExpense()) + " ￦");
        }
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ" +
                "\n                      ● 총 수입 : +" + sumIncomeA + " ￦ ● 총 지출 : -" + sumExpenseA + " ￦\n");
    }

    public void delete() {
        System.out.println("\n=== 메인 → 5.가계부 → " +
                Container.getSeletedAccountBook().getAccountName() + " → 4.내역 → 4.삭제 ===");
        this.readAll();
        System.out.println("삭제할 내역의 날짜와 내용을 입력해주세요.");
        System.out.print("날짜 : ");
        String deleteDate = Container.getSc().nextLine();
        System.out.print("내용 : ");
        String deleteContent = Container.getSc().nextLine();
        historyService.delete(deleteDate, deleteContent);
        System.out.println(deleteDate + "의 내역이 삭제되었습니다.");
    }

    public void update() {
        Container.numFormat();
        System.out.println("\n=== 메인 → 5.가계부 → " +
                Container.getSeletedAccountBook().getAccountName() + " → 4.내역 → 3.수정 ===");
        this.readAll();
        System.out.println("수정할 내역의 날짜와 내용을 입력해주세요.");
        System.out.print("날짜 : ");
        String findDate = Container.getSc().nextLine();
        System.out.print("내용 : ");
        String findContent = Container.getSc().nextLine();
        HistoryDTO historyDTO = historyService.findHistory(findDate, findContent);
        System.out.println("                날짜   |  항목  |   내용    |      수입      |       지출    ");

        String categoryFormat = historyDTO.getCategory();
        if (historyDTO.getCategory().length() == 2) {
            categoryFormat = String.format(" %s ", historyDTO.getCategory());
        }
        String contentFormat = historyDTO.getContent();
        contentFormat += "         ";
        if (historyDTO.getContent().length() == 2) {
            contentFormat = contentFormat.substring(0, 5) + "  ";
        } else if (historyDTO.getContent().length() == 3) {
            contentFormat = contentFormat.substring(0, 5) + " ";
        } else if (historyDTO.getContent().length() == 4) {
            contentFormat = contentFormat.substring(0, 5);
        } else if (historyDTO.getContent().isEmpty()) {
            contentFormat = "         ";
        }
        System.out.println("기존 내역 : " + historyDTO.getDate() + " | " +
                String.format("%s", categoryFormat) + " | " +
                String.format("%s", contentFormat) + " | " +
                String.format("%,11d", historyDTO.getIncome()) + " ￦ | " +
                String.format("%,11d", historyDTO.getExpense()) + " ￦");

        try {
            System.out.print("날짜 입력 : ");
            String updateDate = Container.getSc().nextLine().trim();
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
            int id = historyService.update(updateDate, categoryId, content, income, expense, findDate, findContent);
            if (id == 0) return;
            System.out.println(updateDate + "의 내역이 수정되었습니다.");
        } catch (NumberFormatException e) {
            System.out.println("숫자로 입력해주세요.");
        }
    }
}
