package com.example.pc_.mycode.utils;

import android.app.Activity;

/**
 * Created by pc- on 2017/11/17.
 */

public class CheckManager {

    private static CheckManager checkManager;
    public static CheckManager  newInstance(){
        if(checkManager==null){
            synchronized (CheckManager.class){
                if(checkManager==null){
                    checkManager=new CheckManager();
                }
            }
        }
        return checkManager;
    }




    public  int  checkExist(Activity activity, String[] values){


        boolean hasData=true;
        for (int i=0;i<values.length&&hasData;i++) {
            if (values[i]==null||values[i].equals("") ||values[i].length() <= 0) {
                hasData=false;
                ToastManager.show(activity, "信息不能为空,请认真填写");
            }
        }
        if(hasData){
            return 1;
        }else{
            return 0;
        }

    }

}
