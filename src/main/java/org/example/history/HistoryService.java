package org.example.history;

public class HistoryService {
    HistoryRepository historyRepository;
    HistoryService() {
        historyRepository = new HistoryRepository();
    }
    public int create(String createDate, int categoryId, String content,int income, int expense) {
        return historyRepository.create(createDate, categoryId, content, income, expense);
    }
    public void read() {

    }
    public void delete() {

    }
    public void update() {

    }
}
