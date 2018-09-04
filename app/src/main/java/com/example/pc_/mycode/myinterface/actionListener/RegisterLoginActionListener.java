package com.example.pc_.mycode.myinterface.actionListener;

import com.example.pc_.mycode.myinterface.commonListener.IStatusListener;

/**
 * Created by pc- on 2018/5/31.
 */

public interface RegisterLoginActionListener {


    //在本项目中，将接口分为view，action，complete,后缀都为listener
    void register(String account, String area, String password, IStatusListener registerCompleteListener);
    void login(String account, String area, String password, IStatusListener registerCompleteListener);
    void findPassword(String account, String area, String password, IStatusListener registerCompleteListener);

}
