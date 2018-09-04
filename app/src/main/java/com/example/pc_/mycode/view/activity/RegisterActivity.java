package com.example.pc_.mycode.view.activity;

import android.content.Intent;
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

public class RegisterActivity extends BaseActivity implements CommonHandleListener, View.OnClickListener{
    @BindView(R.id.register_accountEdit)
    EditText registerAccountEdit;
    @BindView(R.id.register_linear1)
    TextView registerLinear1;
    @BindView(R.id.register_passEdit)
    EditText registerPassEdit;
    @BindView(R.id.register_linear11)
    TextView registerLinear11;
    @BindView(R.id.register_areaEdit)
    EditText registerAreaEdit;
    @BindView(R.id.register_linear2)
    TextView registerLinear2;
    @BindView(R.id.register_clickText)
    TextView registerClickText;
    @BindView(R.id.find_pass)
    TextView findPass;
    @BindView(R.id.register_login)
    TextView registerLogin;
    @BindView(R.id.register_progress)
    ProgressBar registerProgress;
    @BindView(R.id.register_type)
    TextView registerType;
    @BindView(R.id.register_layout)
    RelativeLayout registerLayout;


    public CommonPresenter commonPresenter;

    @Override
    public int getLayout() {
        return R.layout.register_layout;
    }

    @Override
    public void initInstance() {
        commonPresenter=new CommonPresenter();
        commonPresenter.setCommonHandleListener(this);
        commonPresenter.setModel("registerLogin");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
          registerAccountEdit.setOnClickListener(this);
          registerPassEdit.setOnClickListener(this);
          registerAreaEdit.setOnClickListener(this);
          findPass.setOnClickListener(this);
          registerLogin.setOnClickListener(this);
    }




    @Override
    public void begin() {
        registerLayout.setVisibility(View.VISIBLE);
        registerType.setText("注册中...");
    }



    @Override
    public void success(Object object) {

        ToastManager.show(this,(String)object);
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void fail(Object object) {
        ToastManager.show(this,(String)object);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.find_pass:
                break;
            case R.id.register_login:
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register_clickText:
                String account=registerAccountEdit.getText().toString().trim();
                String password=registerPassEdit.getText().toString().trim();
                String area=registerAreaEdit.getText().toString().trim();
                String[] values={account,area,password};
                int count=CheckManager.newInstance().checkExist(RegisterActivity.this,values);
                if(count==1){
                    commonPresenter.register(values);
                }

                break;

        }

    }
}
