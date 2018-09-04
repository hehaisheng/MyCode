package com.example.pc_.mycode.retrofit;

import com.example.pc_.mycode.model.bean.ContentBean;
import com.example.pc_.mycode.model.bean.GoodShopBean;
import com.example.pc_.mycode.model.bean.GoodsBean;
import com.example.pc_.mycode.model.bean.NewsBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by pc- on 2017/10/1.
 */

public interface MyApi {


    //这种写法的话，服务器是按照返回的数据类型分类的


    //新的写法，可以将返回值一样的方法整合成一个方法，更加便捷

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("{action}")
    Observable<NewsBean>  executeNewBean(@Path("action") String action,@Body RequestBody registerBody);//传入的参数为RequestBody

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("{action}")
    Observable<GoodsBean.SubGoodsBean>  executeGoodsBean(@Body RequestBody registerBody);//传入的参数为RequestBody


    //新的写法，可以将返回值一样的方法整合成一个方法，更加便捷

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("{action}")
    Observable<ContentBean>  executeContentBean(@Path("action") String action, @Body RequestBody registerBody);//传入的参数为RequestBody



    //下面的写法是可以通过方法名知道该方法的作用
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("register")
    Observable<NewsBean> register(@Body RequestBody registerBody);//传入的参数为RequestBody

    //登录，注册，忘记,更改区域
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("login")
    Observable<NewsBean> login(@Body RequestBody registerBody);//传入的参数为RequestBody

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("findPassword")
    Observable<NewsBean> findPassword(@Body RequestBody registerBody);//传入的参数为RequestBody

    //获取优秀的商店

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("fetchGoodShop")
    Observable<GoodShopBean.SubGoodShopBean> fetchGoodShop(@Body RequestBody registerBody);//传入的参数为RequestBody


    //获取不同类型的商品
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("fetchGoods")
    Observable<GoodsBean.SubGoodsBean> fetchGoods(@Body RequestBody registerBody);//传入的参数为RequestBody

    //搜索商品名，like匹配
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("findGoods")
    Observable<GoodsBean.SubGoodsBean> findGoods(@Body RequestBody registerBody);//传入的参数为RequestBody



    //获取商店的全部商品
    //搜索商品名，like匹配
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("fetchShopGoods")
    Observable<GoodsBean.SubGoodsBean> fetchShopGoods(@Body RequestBody registerBody);//传入的参数为RequestBody





}
