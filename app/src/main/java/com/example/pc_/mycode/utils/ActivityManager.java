package com.example.pc_.mycode.utils;

import android.app.Activity;

import com.example.pc_.mycode.view.MyApplication;

import java.util.HashMap;

/**
 * Created by pc- on 2018/1/18.
 */

public class ActivityManager {

    public static void handleActivity(String name){
        HashMap<String,Activity>  activityHashMap1=new HashMap<>();
        HashMap<String,Activity>  activityHashMap= MyApplication.newInstance().getActivityHashMap();
        for(String key:activityHashMap.keySet()){
            if(!key.equals(name)){
                activityHashMap1.put(key,activityHashMap.get(key));
            }
        }
        MyApplication.newInstance().setActivityHashMap(activityHashMap1);
    }
    public static  void handleAll(){
        HashMap<String,Activity> activityHashMap=MyApplication.newInstance().getActivityHashMap();
        for(String key:activityHashMap.keySet()){
            activityHashMap.get(key).finish();
        }
    }
}
