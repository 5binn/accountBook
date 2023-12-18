package org.example.wiseSaying;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
public class WiseSaying {

    int wiseSayingId;
    String wiseSaying;

    WiseSaying(Map<String, Object> row) {
        this.wiseSayingId = (int) row.get("wiseSayingId");
        this.wiseSaying = (String) row.get("wiseSaying");
    }
}
