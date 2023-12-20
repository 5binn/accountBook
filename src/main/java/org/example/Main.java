package org.example;

import org.example.Global.Container;


public class Main {
    public static void main(String[] args) {
        Container.initScanner();
        new App().run();
        Container.closeScanner();
        //목표, 잔액 어떻게 처리할껀지 가계부에 공유된 이름 표시


    }
}