package com.example.pc_.mycode.view.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc_.mycode.R;
import com.example.pc_.mycode.myinterface.commonListener.CommonHandleListener;
import com.example.pc_.mycode.presenter.CommonPresenter;
import com.example.pc_.mycode.utils.CheckManager;
import com.example.pc_.mycode.utils.ToastManager;

import butterknife.BindView;

/**
 * Created by pc- on 2018/5/31.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, CommonHandleListener {

    @BindView(R.id.login_accountEdit)
    EditText loginAccountEdit;
    @BindView(R.id.login_linear1)
    TextView loginLinear1;
    @BindView(R.id.login_passEdit)
    EditText loginPassEdit;
    @BindView(R.id.login_linear11)
    TextView loginLinear11;
    @BindView(R.id.login_areaEdit)
    EditText loginAreaEdit;
    @BindView(R.id.login_linear2)
    TextView loginLinear2;
    @BindView(R.id.login_clickText)
    TextView loginClickText;
    @BindView(R.id.find_pass)
    TextView findPass;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.login_type)
    TextView loginType;
    @BindView(R.id.login_layout)
    RelativeLayout loginLayout;


    public CommonPresenter commonPresenter;

    @Override
    public int getLayout() {
        return R.layout.login_activity;
    }

    @Override
    public void initInstance() {
       commonPresenter=new CommonPresenter();
       commonPresenter.setCommonHandleListener(this);
       //设置模型
       commonPresenter.setModel("registerLogin");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

        loginClickText.setOnClickListener(this);
        findPass.setOnClickListener(this);
        loginRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.login_clickText:
                break;
            case R.id.find_pass:
                break;
            case R.id.login_register:
                String account=loginAccountEdit.getText().toString().trim();
                String password=loginPassEdit.getText().toString().trim();
                String area=loginAreaEdit.getText().toString().trim();
                String[] values={account,area,password};
                int count= CheckManager.newInstance().checkExist(LoginActivity.this,values);
                if(count==1){
                    commonPresenter.login(values);
                }
                break;

        }
    }





    @Override
    public void begin() {
        loginLayout.setVisibility(View.VISIBLE);
        loginType.setText("登录中...");
    }

    @Override
    public void success(Object object) {
       ToastManager.show(this,(String)object);
    }

    @Override
    public void fail(Object object) {
       ToastManager.show(this,(String)object);
    }
}
