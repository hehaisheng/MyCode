package com.example.pc_.mycode.myinterface.actionListener;

import com.example.pc_.mycode.myinterface.commonListener.CommonCompleteListener;

/**
 * Created by pc- on 2018/6/10.
 */

public interface ShopActionListener {
    //关于商店的接口
    void fetchGoodShop(String[] values, CommonCompleteListener commonCompleteListener);
    void fetchGoods(String[] values, CommonCompleteListener commonCompleteListener);
    void findGoods(String[] values, CommonCompleteListener commonCompleteListener);
    void fetchShopGoods(String[] values, CommonCompleteListener commonCompleteListener);
    void deleteShopGoods(String[] values, CommonCompleteListener commonCompleteListener);
}
