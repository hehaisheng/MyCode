package com.example.pc_.mycode.retrofit;

import com.example.pc_.mycode.model.bean.ContentBean;
import com.example.pc_.mycode.model.bean.GoodsBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by pc- on 2018/7/25.
 */

public interface ApiService {



    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("{action}")
    Observable<GoodsBean.SubGoodsBean> executeGoodsBean(@Body RequestBody registerBody);//传入的参数为RequestBody


    //新的写法，可以将返回值一样的方法整合成一个方法，更加便捷

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("{action}")
    Observable<ContentBean>  executeContentBean(@Path("action") String action, @Body RequestBody registerBody);//传入的参数为RequestBody


}
