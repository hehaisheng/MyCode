package com.example.pc_.mycode.presenter;

import com.example.pc_.mycode.model.implement.RegisterLoginModel;
import com.example.pc_.mycode.model.implement.ShopActionModel;
import com.example.pc_.mycode.myinterface.commonListener.CommonCompleteListener;
import com.example.pc_.mycode.myinterface.commonListener.CommonHandleListener;
import com.example.pc_.mycode.myinterface.commonListener.IStatusListener;

/**
 * Created by pc- on 2018/6/10.
 */

public class CommonPresenter {

    //代理，具体的实现在model
    private RegisterLoginModel registerLoginModel;
    private ShopActionModel shopActionModel;

    private CommonHandleListener commonHandleListener;
    public void setCommonHandleListener(CommonHandleListener commonHandleListener) {
        this.commonHandleListener = commonHandleListener;
    }


    public void setModel(String modelName){
        switch (modelName){
            case "registerLogin":
                registerLoginModel=new RegisterLoginModel();
                break;
            case "shopAction":
                shopActionModel=new ShopActionModel();
                break;
        }

    }

    public void register(String[] values){
        commonHandleListener.begin();
        registerLoginModel.register(values[0],values[1],values[2], new IStatusListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);
            }



        });

    }
    public void login(String[] values){
        commonHandleListener.begin();
        registerLoginModel.login(values[0],values[1],values[2], new IStatusListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);
            }



        });

    }

    public void findPassword(String[] values){
        commonHandleListener.begin();
        registerLoginModel.findPassword(values[0],values[1],values[2], new IStatusListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);
            }



        });
    }
    public void fetchGoodShop(String[] values){
        commonHandleListener.begin();
        shopActionModel.fetchGoodShop(values, new CommonCompleteListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);

            }
        });
    }
    public void fetchGoods(String[] values){
        commonHandleListener.begin();
        shopActionModel.fetchGoods(values, new CommonCompleteListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);

            }
        });
    }
    public void findGoods(String[] values){

        commonHandleListener.begin();
        shopActionModel.findGoods(values, new CommonCompleteListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);

            }
        });
    }
    public void fetchShopGoods(String[] values){
        commonHandleListener.begin();
        shopActionModel.fetchShopGoods(values, new CommonCompleteListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);

            }
        });
    }


    public void deleteShopGoods(String[] values){
        commonHandleListener.begin();
        shopActionModel.deleteShopGoods(values, new CommonCompleteListener() {
            @Override
            public void success(Object object) {
                commonHandleListener.success(object);
            }

            @Override
            public void fail(Object object) {
                commonHandleListener.fail(object);

            }
        });
    }



}
