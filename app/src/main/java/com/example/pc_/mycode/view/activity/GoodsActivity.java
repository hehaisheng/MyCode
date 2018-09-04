package com.example.pc_.mycode.view.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc_.mycode.R;
import com.example.pc_.mycode.model.bean.GoodsBean;
import com.example.pc_.mycode.myinterface.commonListener.AdapterClickListener;
import com.example.pc_.mycode.myinterface.commonListener.CommonHandleListener;
import com.example.pc_.mycode.presenter.CommonPresenter;
import com.example.pc_.mycode.utils.ScrollManager;
import com.example.pc_.mycode.utils.SharedPreferencesManager;
import com.example.pc_.mycode.utils.ToastManager;
import com.example.pc_.mycode.view.adapter.GoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    }



    @Override
    public int getLayout() {
        return R.layout.goods_activity;
    }

    @Override
    public void initInstance() {
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
    }

    @Override
    public void initData() {

        String[] values={"fetchGoods",areaString,goodsType,"0"};
        commonPresenter.fetchGoods(values);
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
        GoodsBean newsBean = ((GoodsBean) object);
        goodsLayout.setVisibility(View.GONE);
        if(newsBean.getTotal()>0){
            if(isLoad){
                length=newsBean.getSubGoodsBeans().size()+length;
                for(int i=0;i<newsBean.getSubGoodsBeans().size();i++){
                    this.subGoodsBeanList.add(newsBean.getSubGoodsBeans().get(i));
                }
            }else{
                length=newsBean.getSubGoodsBeans().size();
                this.subGoodsBeanList=newsBean.getSubGoodsBeans();
            }
            goodsAdapter=new GoodsAdapter(this,R.layout.main_item,this.subGoodsBeanList);
            goodsRecycler.setAdapter(goodsAdapter);
            setListener(goodsAdapter);

        }else{
            ToastManager.show(this,"暂无更多商品数据");
        }

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
