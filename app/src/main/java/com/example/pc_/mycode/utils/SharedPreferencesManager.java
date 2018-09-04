package com.example.pc_.mycode.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pc- on 2017/11/17.
 */

public class SharedPreferencesManager {

    private static SharedPreferencesManager sharedPreferencesManager;
    private SharedPreferences sharedPreferences;
    public String account;
    public static SharedPreferencesManager  newInstance(Context context){
        if(sharedPreferencesManager==null){
            synchronized (SharedPreferencesManager.class){
                if(sharedPreferencesManager==null){
                    sharedPreferencesManager=new SharedPreferencesManager(context);
                }
            }
        }
        return sharedPreferencesManager;
    }

    private SharedPreferencesManager(Context context){
        sharedPreferences=context.getSharedPreferences("MyCode",0);

    }

    public String getPreference(String columns){
        return sharedPreferences.getString(columns,"0");
    }
    public void putPreference(String columns,String values){
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString(columns,values);
        editor.apply();

    }
    public String[]  getPreferences(String[] columns){
        String[] values=new String[columns.length];
        for(int i=0;i<columns.length;i++){
            values[i]=sharedPreferences.getString(columns[i],"0");
        }
       return values;
    }

    public void putPreferences(String[] columns,String[] values){
        for(int i=0;i<columns.length;i++){
            SharedPreferences.Editor  editor = sharedPreferences.edit();
            editor.putString(columns[i], values[i]);
            editor.apply();
        }
    }
    public void putBoolean(String column,boolean  hasBoolean){

            SharedPreferences.Editor  editor = sharedPreferences.edit();
            editor.putBoolean(column,hasBoolean);
            editor.apply();

    }
    public boolean  getBoolean(String column){

           return sharedPreferences.getBoolean(column,false);
    }

    public String getPreferenceByAccount(String columns){
        account=sharedPreferences.getString("account","0");
        return sharedPreferences.getString(account+columns,"0");
    }
    public String[]  getPreferencesByAccount(String[] columns){
        account=sharedPreferences.getString("account","0");
        String[] values=new String[columns.length];
        for(int i=0;i<columns.length;i++){
            values[i]=sharedPreferences.getString(account+columns[i],"0");
        }
        return values;
    }

    public void putPreferencesByAccount(String[] columns,String[] values){
        account=sharedPreferences.getString("account","0");
        for(int i=0;i<columns.length;i++){
            SharedPreferences.Editor  editor = sharedPreferences.edit();
            editor.putString(account+columns[i], values[i]);
            editor.apply();
        }
    }

    public void putPreferenceByAccount(String column,String value){
        account=sharedPreferences.getString("account","0");
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString(account+column,value);
        editor.apply();

    }
    public void putBooleanByAccount(String column,boolean  hasBoolean){
        account=sharedPreferences.getString("account","0");
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putBoolean(account+column,hasBoolean);
        editor.apply();

    }
    public boolean  getBooleanByAccount(String column){
        account=sharedPreferences.getString("account","0");
        return sharedPreferences.getBoolean(account+column,false);
    }

}
