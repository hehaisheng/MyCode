package com.example.pc_.mycode.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pc- on 2017/11/7.
 */

public class DateManager {

    private static DateManager dateManager;
    public static DateManager newInstance(){
        if(dateManager==null){
            synchronized (DateManager.class){
                if(dateManager==null){
                    dateManager=new DateManager();
                }
            }
        }
        return  dateManager;
    }

    //获取当前时间
    public String getSimpleDate(){

        String formatString="yyyy年MM月dd日 HH:mm:ss";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }
    //获取当前时间,到天
    public String getSimpleDayDate(){

        String formatString="yyyy年MM月dd日";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    //获取年，月，日
    public String[]  getCurrentCalendar(){
        Calendar calendar= Calendar.getInstance();
        String[] dates=new String[3];
        dates[0]=calendar.get(Calendar.YEAR)+"";
        dates[1]=(calendar.get(Calendar.MONTH)+1)+"";
        dates[2]=calendar.get(Calendar.DATE)+"";
        return dates;


    }




}
