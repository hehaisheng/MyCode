package com.example.pc_.mycode.model.implement;

import com.example.pc_.mycode.model.bean.GoodShopBean;
import com.example.pc_.mycode.model.bean.GoodsBean;
import com.example.pc_.mycode.myinterface.actionListener.ShopActionListener;
import com.example.pc_.mycode.myinterface.commonListener.CommonCompleteListener;
import com.example.pc_.mycode.myinterface.commonListener.IStatusListener;
import com.example.pc_.mycode.retrofit.ConnectServer;
import com.example.pc_.mycode.view.MyApplication;

/**
 * Created by pc- on 2018/6/10.
 */

public class ShopActionModel implements ShopActionListener {
    //获取商品和商店
    @Override
    public void fetchGoodShop(String[] values, final CommonCompleteListener commonCompleteListener) {



        String[] columns={"action","area","start"};

        //在哪个区域从哪里开始获取
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                GoodShopBean newsBean = ((GoodShopBean) object);
                commonCompleteListener.success(newsBean);


            }

            @Override
            public void fail(Object object) {
                commonCompleteListener.fail(object);

            }
        });

        connectServer.toServer("fetchGoodShop", columns, values, MyApplication.newInstance());
    }

    @Override
    public void fetchGoods(String[] values,final CommonCompleteListener commonCompleteListener) {
        String[] columns={"action","area","type","start"};

        //在哪个区域从哪里开始获取
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                GoodsBean newsBean = ((GoodsBean) object);
                commonCompleteListener.success(newsBean);

            }

            @Override
            public void fail(Object object) {
                commonCompleteListener.fail(object);

            }
        });

        connectServer.toServer("fetchGoods", columns, values, MyApplication.newInstance());
    }

    @Override
    public void findGoods(String[] values, final CommonCompleteListener commonCompleteListener) {
        String[] columns={"action","area","goodsName","start"};

        //在哪个区域从哪里开始获取
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                GoodsBean newsBean = ((GoodsBean) object);
                commonCompleteListener.success(newsBean);
            }

            @Override
            public void fail(Object object) {
                commonCompleteListener.fail(object);

            }
        });

        connectServer.toServer("findGoods", columns, values, MyApplication.newInstance());
    }

    @Override
    public void fetchShopGoods(String[] values, final CommonCompleteListener commonCompleteListener) {
        String[] columns={"action","area","shopName","start"};

        //在哪个区域从哪里开始获取
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                GoodsBean newsBean = ((GoodsBean) object);
                commonCompleteListener.success(newsBean);
            }

            @Override
            public void fail(Object object) {
                commonCompleteListener.fail(object);

            }
        });

        connectServer.toServer("fetchShopGoods", columns, values, MyApplication.newInstance());
    }

    @Override
    public void deleteShopGoods(String[] values, final CommonCompleteListener commonCompleteListener) {
        String[] columns={"action","area","shopName"};

        //在哪个区域从哪里开始获取
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                GoodsBean newsBean = ((GoodsBean) object);
                commonCompleteListener.success(newsBean);
            }

            @Override
            public void fail(Object object) {
                commonCompleteListener.fail(object);

            }
        });

        connectServer.toServer("newBean", columns, values, MyApplication.newInstance());

    }

}
