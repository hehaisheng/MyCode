package com.example.pc_.mycode.retrofit;

import android.content.Context;

import com.example.pc_.mycode.model.bean.ContentBean;
import com.example.pc_.mycode.model.bean.GoodShopBean;
import com.example.pc_.mycode.model.bean.GoodsBean;
import com.example.pc_.mycode.model.bean.NewsBean;
import com.example.pc_.mycode.utils.Constants;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by pc- on 2017/10/1.
 */

public class FetchNews {

//192.168.43.142

    public  static  FetchNews  fetchNews;
    public Context context;

    public static FetchNews newInstance(Context context){
        if(fetchNews==null){
            synchronized (FetchNews.class){
                if(fetchNews==null){
                    fetchNews=new FetchNews(context);
                }
            }
        }
        return fetchNews;
    }

    private FetchNews(Context context){
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



    //注册，登录，忘记，退出
    Observable<NewsBean> register(RequestBody requestBody){
        return newInstance().create(MyApi.class).register(requestBody);
    }

    Observable<NewsBean> login(RequestBody requestBody){
        return newInstance().create(MyApi.class).login(requestBody);
    }

    Observable<NewsBean> findPassword(RequestBody requestBody){
        return newInstance().create(MyApi.class).findPassword(requestBody);
    }
    Observable<GoodShopBean.SubGoodShopBean>  fetchGoodShop(RequestBody requestBody){
        return newInstance().create(MyApi.class).fetchGoodShop(requestBody);
    }
    Observable<GoodsBean.SubGoodsBean>  fetchGoods(RequestBody requestBody){
        return newInstance().create(MyApi.class).fetchGoods(requestBody);
    }
    public Observable<GoodsBean.SubGoodsBean>  findGoods(RequestBody   requestBody){
        return newInstance().create(MyApi.class).findGoods(requestBody);
    }

    public Observable<GoodsBean.SubGoodsBean>  fetchShopGoods(RequestBody   requestBody){
        return newInstance().create(MyApi.class).fetchShopGoods(requestBody);
    }

    Observable<ContentBean>  executeContentBean(String actionString, RequestBody requestBody){
        return newInstance().create(MyApi.class).executeContentBean(actionString,requestBody);
    }




}
