package com.example.projectboard.domain;

import javax.validation.constraints.Null;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.out;

public class Logger {
    public static void log(int level, String message){
        String info = "INFO"; //1
        String trace = "TRACE"; //2
        String warning = "WARNING"; //3
        String error = "ERROR"; //4
        String critical = "CRITICAL"; //5

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        String pattern = "***";

        switch (level){
            case 1:
                pattern+=info;
                break;
            case 2:
                pattern+=trace;
                break;
            case 3:
                pattern+=warning;
                break;
            case 4:
                pattern+=error;
                break;
            case 5:
                pattern+=critical;
                break;
            default:
                pattern = ">UNKNOWN<";
                break;
        }
        pattern+="***;";
        pattern+=strDate;
        pattern+=";";
        pattern+=message;
        PrintWriter pw = null;
        try {
            File file = new File("gira.log");
            FileWriter fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
            pw.println(pattern);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

    }
}
