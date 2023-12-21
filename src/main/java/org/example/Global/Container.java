package org.example.Global;

import lombok.Getter;
import lombok.Setter;
import org.example.DB.DBConnection;
import org.example.accountBook.AccountBook;
import org.example.member.Member;
import org.example.wiseSaying.WiseSaying;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Container {
    @Getter
    private static Scanner sc;
    private static DBConnection dbConnection;
    @Setter
    @Getter
    private static Member loggedInMember;
    @Setter
    @Getter
    private static AccountBook seletedAccountBook;
    @Getter
    private static WiseSaying wiseSayings;
    public static DecimalFormat df;
    @Getter
    @Setter

    private static String command;
    public static void numFormat() {
        df = new DecimalFormat("#,###");
    }
    public static void initScanner() {
        sc = new Scanner(System.in);
    }

    public static void closeScanner() {
        sc.close();
    }
    public static DBConnection getDbConnection() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
    public static String nowDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        return format.format(time);
    }

    public static int decimalToInteger(BigDecimal decimal) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(decimal));
        return bigDecimal.intValue();
    }

    public static void setWiseSaying(WiseSaying wiseSaying) {
        wiseSayings =  wiseSaying;
    }
}
