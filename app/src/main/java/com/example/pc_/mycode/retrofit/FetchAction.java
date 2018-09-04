package com.example.pc_.mycode.retrofit;

import android.content.Context;

import com.example.pc_.mycode.model.bean.ContentBean;
import com.example.pc_.mycode.utils.Constants;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by pc- on 2018/7/25.
 */

public class FetchAction {


    public  static  FetchAction  fetchAction;
    public Context context;

    public static FetchAction newInstance(Context context){
        if(fetchAction==null){
            synchronized (FetchAction.class){
                if(fetchAction==null){
                    fetchAction=new FetchAction(context);
                }
            }
        }
        return  fetchAction;
    }

    private FetchAction(Context context){
        this.context=context.getApplicationContext();
    }


    public Retrofit newInstance()
    {
        String BASE_URL = Constants.baseUrl;
        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpManager.newInstance().createClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    Observable<ContentBean> executeContentBean(String actionString, RequestBody requestBody){
        return newInstance().create(MyApi.class).executeContentBean(actionString,requestBody);
    }

}
