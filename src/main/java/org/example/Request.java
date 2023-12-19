package org.example;

public class Request {
    public String actionCode;
    public String[] commandList;
    public String readCommand;
    public Request(String command) {
        switch (command) {
            case "1":
                command = "등록";
                break;
            case "2":
                command = "보기";
                break;
            case "3":
                command = "수정";
                break;
            case "4":
                command = "삭제";
                break;
            case "5":
                return;
        }
        commandList = command.split(":", 2);
        actionCode = commandList[0];
        if (commandList.length == 1) return;
        readCommand = commandList[1];
    }

}
