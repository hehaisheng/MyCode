package com.example.pc_.mycode.view;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;

/**
 * Created by pc- on 2018/5/31.
 */

public class MyApplication extends Application {

    public HashMap<String, Activity> getActivityHashMap() {
        return activityHashMap;
    }

    public void setActivityHashMap(HashMap<String, Activity> activityHashMap) {
        this.activityHashMap = activityHashMap;
    }

    public HashMap<String,Activity>  activityHashMap=new HashMap<>();


    public static MyApplication myApplication;
    public static  MyApplication newInstance(){
        if(myApplication==null){
            synchronized (MyApplication.class){
                if(myApplication==null){
                    myApplication=new MyApplication();
                }
            }
        }
        return myApplication;

    }
}
