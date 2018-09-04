package com.example.pc_.mycode.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by pc- on 2017/11/2.
 */

public class ToastManager {

    public static  void show(Activity activity, String content){
        Toast.makeText(activity,content,Toast.LENGTH_SHORT).show();
    }

    public static  void showDialog(Activity activity,String content){
        new AlertDialog.Builder(activity)
                .setTitle(content)
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();


                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();

                    }
                }).show();
    }
}
