package org.example.wiseSaying;

import org.example.Global.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        wiseSayingRepository = new WiseSayingRepository();
    }

    public void create(String wiseSaying) {
        wiseSayingRepository.create(wiseSaying);
    }

    public List<WiseSaying> list() {
        return wiseSayingRepository.findByAll();
    }

    public void delete(int wiseSayingId) {
        wiseSayingRepository.delete(wiseSayingId);
    }

    public void update(String wiseSaying, int wiseSayingId) {
        wiseSayingRepository.update(wiseSaying, wiseSayingId);
    }
    public List<WiseSaying> findByAll() {
        return wiseSayingRepository.findByAll();
    }
    public String findByWiseSaying(int WiseSayingId) {
        return wiseSayingRepository.findByWiseSaying(WiseSayingId);
    }

}
