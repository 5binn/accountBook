package org.example.wiseSaying;

import java.util.Map;

public class WiseSaying {

    int wiseSayingId;
    String wiseSaying;

    WiseSaying(Map<String, Object> row) {
        this.wiseSayingId = (int) row.get("wiseSayingId");
        this.wiseSaying = (String) row.get("wiseSaying");
    }
}
