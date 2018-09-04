package com.example.pc_.mycode.retrofit;


import com.example.pc_.mycode.model.bean.ContentBean;
import com.example.pc_.mycode.model.bean.GoodShopBean;
import com.example.pc_.mycode.model.bean.GoodsBean;
import com.example.pc_.mycode.model.bean.JsonHashMap;
import com.example.pc_.mycode.model.bean.NewsBean;
import com.example.pc_.mycode.myinterface.commonListener.IStatusListener;
import com.example.pc_.mycode.view.MyApplication;
import com.google.gson.Gson;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;

/**
 * Created by pc- on 2017/10/11.
 */

public class ConnectServer {

    private FetchNews fetchNews;
    private Observable<NewsBean> newsBeanObservable;
    private Observable<ContentBean> contentBeanObservable;
    private Observable<GoodShopBean.SubGoodShopBean>  goodShopBeanObservable;
    private  Observable<GoodsBean.SubGoodsBean>  goodsBeanObservable;
    private RequestBody userRegister;


    public void  toServer(final String action, String[] columns, final String[] values, final MyApplication myApplication){


        Gson gson = new Gson();
        if(columns!=null){
            JsonHashMap jsonHashMap=new JsonHashMap(columns,values);
            String userRegisterStr= gson.toJson(jsonHashMap.getHashMap());
            userRegister= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),userRegisterStr);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchNews= FetchNews.newInstance(myApplication);
                //登录，注册，忘记，退出，评价
                switch (action) {
                    case "register":
                        //正确的写法是对于同一个类型的返回值使用同一个处理方法
                        newsBeanObservable = fetchNews.register(userRegister);
                        setNewsBeanObservable(newsBeanObservable);

                        break;
                    case "login":
                        newsBeanObservable=fetchNews.login(userRegister);
                        setNewsBeanObservable(newsBeanObservable);

                        break;
                    case "newBean":
                        contentBeanObservable=fetchNews.executeContentBean(action,userRegister);
                        setContentBeanObservable(contentBeanObservable);
                        break;
                    case "findPassword":
                        newsBeanObservable=fetchNews.findPassword(userRegister);
                        setNewsBeanObservable(newsBeanObservable);
                        break;
                    case "fetchGoodShop":
                        goodShopBeanObservable=fetchNews.fetchGoodShop(userRegister);
                        setGoodShopBeanObservable(goodShopBeanObservable);

                        break;
                    case "fetchGoods":
                        goodsBeanObservable=fetchNews.fetchGoods(userRegister);
                        setGoodsBeanObservable(goodsBeanObservable);
                        break;
                    case "findGoods":
                        goodsBeanObservable=fetchNews.fetchGoods(userRegister);
                        setGoodsBeanObservable(goodsBeanObservable);
                        break;
                    case "fetchShopGoods":
                        goodsBeanObservable=fetchNews.fetchGoods(userRegister);
                        setGoodsBeanObservable(goodsBeanObservable);
                        break;


                }

            }
        }).start();

    }

    private void  setContentBeanObservable(Observable<ContentBean> contentBeanObservable){
        contentBeanObservable.compose(ComSchedulers.<ContentBean>applyIoSchedulers())
                .subscribe(createObservable());
    }
    private void  setNewsBeanObservable(Observable<NewsBean> newsBeanObservable){
        newsBeanObservable.compose(ComSchedulers.<NewsBean>applyIoSchedulers())
                .subscribe(createObservable());
    }
    private void  setGoodShopBeanObservable(Observable<GoodShopBean.SubGoodShopBean> newsBeanObservable){
        newsBeanObservable.compose(ComSchedulers.<GoodShopBean.SubGoodShopBean>applyIoSchedulers())
                .subscribe(createObservable());
    }
    private void  setGoodsBeanObservable(Observable<GoodsBean.SubGoodsBean> newsBeanObservable){
        newsBeanObservable.compose(ComSchedulers.<GoodsBean.SubGoodsBean>applyIoSchedulers())
                .subscribe(createObservable());
    }
    private Observer<Object> createObservable(){
    //也处理失败的情况
        return new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iStatusListener.fail(e);

            }

            @Override
            public void onNext(Object object) {

                iStatusListener.success(object);
            }
        };

    }


    private IStatusListener iStatusListener;
    public void setStatusListener(IStatusListener iStatusListener){
        this.iStatusListener=iStatusListener;
    }


}
