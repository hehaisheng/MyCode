package com.example.pc_.mycode.view.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_.mycode.R;
import com.example.pc_.mycode.model.bean.ContentBean;
import com.example.pc_.mycode.model.bean.GoodsBean;
import com.example.pc_.mycode.myinterface.commonListener.AdapterClickListener;
import com.example.pc_.mycode.myinterface.commonListener.CommonHandleListener;
import com.example.pc_.mycode.presenter.CommonPresenter;
import com.example.pc_.mycode.retrofit.ComSchedulers;
import com.example.pc_.mycode.retrofit.Constants;
import com.example.pc_.mycode.retrofit.RetrofitFactory;
import com.example.pc_.mycode.retrofit.TransformerUtils;
import com.example.pc_.mycode.utils.ScrollManager;
import com.example.pc_.mycode.utils.SharedPreferencesManager;
import com.example.pc_.mycode.utils.ToastManager;
import com.example.pc_.mycode.view.adapter.GoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observer;

/**
 * Created by pc- on 2017/8/13.
 */
public class GoodsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, CommonHandleListener {


    //显示不同类型的商品
    @BindView(R.id.goods_all_money)
    TextView goodsAllMoney;
    @BindView(R.id.goods_head_title)
    TextView goodsHeadTitle;
    @BindView(R.id.goods_recycler)
    RecyclerView goodsRecycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.goods_delete_pay)
    ImageView goodsDeletePay;
    @BindView(R.id.goods_progress)
    ProgressBar goodsProgress;
    @BindView(R.id.goods_content)
    TextView goodsContent;
    @BindView(R.id.goods_layout)
    RelativeLayout goodsLayout;
    @BindView(R.id.goods_show)
    TextView goodsShow;



    public GoodsAdapter goodsAdapter;
    public List<GoodsBean.SubGoodsBean> subGoodsBeanList=new ArrayList<>();
    public CommonPresenter commonPresenter;
    public SharedPreferencesManager sharedPreferencesManager;

    public String goodsType;
    public String areaString;
    public boolean isLoad=false;
    public int length=0;

    @Override
    public void onRefresh() {
        length=0;
        String[] values={"fetchGoods",areaString,goodsType,"0"};
        commonPresenter.fetchGoods(values);
        isLoad=false;
        swipeLayout.setRefreshing(true);
    }



    @Override
    public int getLayout() {
        return R.layout.goods_activity;
    }

    @Override
    public void initInstance() {
        SmsManager smsManager = SmsManager.getDefault();//默认的短信管理器
        String[] phones = new String[]{"13556528992","13556528992","13556528992","13556528992"};

        for(int i=0;i<phones.length;i++){

          String content = "短信内容"+i;//短信内容



            PendingIntent sentIntent = PendingIntent.getBroadcast(GoodsActivity.this, 0, new Intent(), 0);
//如果字数超过70,需拆分成多条短信发送
            List<String> msgs = smsManager.divideMessage(content);
            for(String msg : msgs){
                smsManager.sendTextMessage(phones[i], null, msg, sentIntent, null);
//第四个参数用于告之短信发送状态,最后一个参数用于告之短信接收状态
            }
            Toast.makeText(GoodsActivity.this, "短信发送完成", Toast.LENGTH_LONG).show();




//          String content = "短信内容";//短信内容
//          String phone = "18148763632";//电话号码
//           android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
//
//           java.util.List<String> texts = smsManager.divideMessage(content);
//
//           for (String text :texts) {
//               smsManager.sendTextMessage(phone, null, text, null,null);
//          }


         }

        sharedPreferencesManager=SharedPreferencesManager.newInstance(this);
        areaString=sharedPreferencesManager.getPreferenceByAccount("area");
        goodsType=getIntent().getStringExtra("goodsType");
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        goodsRecycler.setLayoutManager(new LinearLayoutManager(this));

        commonPresenter=new CommonPresenter();
        commonPresenter.setCommonHandleListener(this);

        commonPresenter.setModel("shopAction");
        goodsRecycler= ScrollManager.newInstance().setScrollListener(goodsRecycler,this);
        ScrollManager.newInstance().setListener(new ScrollManager.ScrollListener() {
            @Override
            public void scroll() {

                isLoad=true;
                String[] values={"fetchGoods",areaString,goodsType,length+""};
                commonPresenter.fetchGoods(values);

            }
        });
        com.example.pc_.mycode.newCode.CommonPresenter.newInstance().setCommonHandleListener(this);
    }

    @Override
    public void initData() {

        //String[] values={"fetchGoods",areaString,goodsType,"0"};
        //commonPresenter.fetchGoods(values);
        ContentBean contentBean=new ContentBean();
        com.example.pc_.mycode.newCode.CommonPresenter.newInstance().execute(contentBean,"http://192.168.1.7:8007/userLogin/loginByPassword/");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void begin() {

        goodsLayout.setVisibility(View.VISIBLE);
        goodsShow.setText("加载中...");
    }

    @Override
    public void success(Object object) {


//
//        GoodsBean newsBean = ((GoodsBean) object);
//        goodsLayout.setVisibility(View.GONE);
//        if(newsBean.getTotal()>0){
//            if(isLoad){
//                length=newsBean.getSubGoodsBeans().size()+length;
//                this.subGoodsBeanList.addAll(newsBean.getSubGoodsBeans());
//            }else{
//                length=newsBean.getSubGoodsBeans().size();
//                this.subGoodsBeanList=newsBean.getSubGoodsBeans();
//            }
//            goodsAdapter=new GoodsAdapter(this,R.layout.main_item,this.subGoodsBeanList);
//            goodsRecycler.setAdapter(goodsAdapter);
//            setListener(goodsAdapter);
//
//        }else{
//            ToastManager.show(this,"暂无更多商品数据");
//        }

    }

    @Override
    public void fail(Object object) {
        ToastManager.show(this,"服务器出小差了");
    }

    public  void setListener(GoodsAdapter goodsAdapter){

        goodsAdapter.setListener(new AdapterClickListener() {
            @Override
            public void handleClick(String[] values) {

            }
        });
    }

}
