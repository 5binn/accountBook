package org.example.wiseSaying;

import org.example.Global.Container;
import org.example.accountBook.AccountBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WiseSayingRepository {

    public void create(String wiseSaying) {
        String sql = String.format("INSERT INTO `wiseSaying`" +
                "SET wiseSaying = '%s' ", wiseSaying);
        int id = Container.getDbConnection().insert(sql);

    }

    public void delete(int wiseSayingId) {
        String sql = String.format("DELETE FROM `wiseSaying` WHERE wiseSayingId = %d;", wiseSayingId);
        Container.getDbConnection().delete(sql);
    }

    public void update(String wiseSaying, int wiseSayingId) {
        String sql = String.format("UPDATE `wiseSaying` SET wiseSaying = '%s' WHERE wiseSayingId = %d;", wiseSaying, wiseSayingId);
        Container.getDbConnection().update(sql);

    }

    public List<WiseSaying> findByAll() {
        List<WiseSaying> wiseSayingList = new ArrayList<>();
        String sql = "SELECT * FROM `wiseSaying`";
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            WiseSaying member = new WiseSaying(row);
            wiseSayingList.add(member);
        }
        return wiseSayingList;
    }

    public String findByWiseSaying(int WiseSayingId) {
        String sql = String.format("SELECT * FROM `WiseSaying` WHERE WiseSayingId = %d;", WiseSayingId);
        Map<String, Object> row = Container.getDbConnection().selectRow(sql);
        WiseSaying wiseSaying = new WiseSaying(row);
        return wiseSaying.getWiseSaying();
    }
}
