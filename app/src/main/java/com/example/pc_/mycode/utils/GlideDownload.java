package com.example.pc_.mycode.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by pc- on 2017/10/26.
 */

public class GlideDownload {

    private static GlideDownload glideDownload;
    public static GlideDownload  newInstance(){
        if(glideDownload==null){
            synchronized (GlideDownload.class){
                if(glideDownload==null){
                    glideDownload=new GlideDownload();
                }
            }
        }
        return glideDownload;
    }
    public  void  download(Activity activity, String netPath, ImageView imageView) {

        String imagePath=Constants.baseUrl+"ImageServlet?imagePath="+netPath;

        Glide.with(activity).load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }
}
