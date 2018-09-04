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

public class FindPasswordActivity extends BaseActivity implements CommonHandleListener {


    @BindView(R.id.find_accountEdit)
    EditText findAccountEdit;
    @BindView(R.id.find_linear1)
    TextView findLinear1;
    @BindView(R.id.find_areaEdit)
    EditText findAreaEdit;
    @BindView(R.id.find_linear2)
    TextView findLinear2;
    @BindView(R.id.find_passEdit)
    EditText findPassEdit;
    @BindView(R.id.find_linear3)
    TextView findLinear3;
    @BindView(R.id.find_clickText)
    TextView findClickText;
    @BindView(R.id.find_progress)
    ProgressBar findProgress;
    @BindView(R.id.find_content)
    TextView findContent;
    @BindView(R.id.find_layout)
    RelativeLayout findLayout;


    public CommonPresenter commonPresenter;

    @Override
    public int getLayout() {
        return R.layout.find_password_activity;
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
      findClickText.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String account=findAccountEdit.getText().toString().trim();
              String password=findPassEdit.getText().toString().trim();
              String area=findAreaEdit.getText().toString().trim();
              String[] values={account,area,password};
              int count= CheckManager.newInstance().checkExist(FindPasswordActivity.this,values);
              if(count==1){
                  commonPresenter.findPassword(values);
              }
          }
      });
    }

    @Override
    public void begin() {
        findLayout.setVisibility(View.VISIBLE);
        findContent.setText("更换密码中...");
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
