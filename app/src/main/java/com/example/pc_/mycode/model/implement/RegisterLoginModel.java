package com.example.pc_.mycode.model.implement;

import com.example.pc_.mycode.model.bean.NewsBean;
import com.example.pc_.mycode.myinterface.actionListener.RegisterLoginActionListener;
import com.example.pc_.mycode.myinterface.commonListener.IStatusListener;
import com.example.pc_.mycode.retrofit.ConnectServer;
import com.example.pc_.mycode.utils.SharedPreferencesManager;
import com.example.pc_.mycode.view.MyApplication;

/**
 * Created by pc- on 2018/5/31.
 */

public class RegisterLoginModel implements RegisterLoginActionListener {



    //通过retrofit可以在model里改变视图，但是为了分工明确，model只做获取数据和改变数据的操作
    private SharedPreferencesManager sharedPreferencesManager;

    public RegisterLoginModel(){
        sharedPreferencesManager=SharedPreferencesManager.newInstance(MyApplication.newInstance());
    }







    @Override
    public void register(final String account, final String area, final String password, final IStatusListener registerCompleteListener) {


        final String[] columns = {"action", "area", "account", "password"};
        final String[] values = {"register",area, account, password};
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                NewsBean newsBean = ((NewsBean) object);
                String content=newsBean.getContent();
                switch (content) {
                    case "注册成功":
                        //登录成功，跳转主界面，没有就跳转注册登录界面
                        sharedPreferencesManager.putPreference("account",account);
                        sharedPreferencesManager.putPreferencesByAccount(columns,values);
                        registerCompleteListener.success(content);

                        break;
                    case "注册失败":
                        registerCompleteListener.fail(content);
                        break;

                }
            }

            @Override
            public void fail(Object object) {
                registerCompleteListener.fail("服务器累了");
            }
        });

        connectServer.toServer("register", columns, values, MyApplication.newInstance());
    }

    @Override
    public void login(String account, final String area, String password, final IStatusListener registerCompleteListener) {

        final String[] columns = {"action", "area", "account", "password"};
        final String[] values = {"login",area, account, password};
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                NewsBean newsBean = ((NewsBean) object);
                String content=newsBean.getContent();
                switch (content) {
                    case "登录成功":
                        //登录成功，跳转主界面，没有就跳转注册登录界面
                        sharedPreferencesManager.putPreference("hasLogin","true");
                        sharedPreferencesManager.putPreferenceByAccount("area",area);
                        registerCompleteListener.success(content);

                        break;
                    case "登录失败":
                        registerCompleteListener.fail(content);
                        break;

                }
            }

            @Override
            public void fail(Object object) {
                registerCompleteListener.fail("服务器累了");
            }
        });

        connectServer.toServer("login", columns, values, MyApplication.newInstance());
    }

    @Override
    public void findPassword(String account, String area, String password, final IStatusListener registerCompleteListener) {
        final String[] columns = {"action", "area", "account", "password"};
        final String[] values = {"findPassword",area, account, password};
        ConnectServer connectServer = new ConnectServer();
        connectServer.setStatusListener(new IStatusListener() {
            @Override
            public void success(Object object) {
                NewsBean newsBean = ((NewsBean) object);
                String content=newsBean.getContent();
                switch (newsBean.getContent()) {
                    case "更换成功":
                        //登录成功，跳转主界面，没有就跳转注册登录界面
                        registerCompleteListener.success(content);

                        break;
                    case "更换失败":
                        registerCompleteListener.fail(content);
                        break;

                }
            }

            @Override
            public void fail(Object object) {
                registerCompleteListener.fail("服务器累了");
            }
        });

        connectServer.toServer("findPassword", columns, values, MyApplication.newInstance());
    }
}
