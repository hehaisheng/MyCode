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
import com.example.pc_.mycode.view.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/13.
 */
public class ShopActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, CommonHandleListener {

    //显示商店的商品

    @BindView(R.id.shop_all_money)
    TextView shopAllMoney;
    @BindView(R.id.shop_head_title)
    TextView shopHeadTitle;
    @BindView(R.id.shop_recycler)
    RecyclerView recycler;
    @BindView(R.id.shop_SwipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.shop_delete_pay)
    ImageView shopDeletePay;
    @BindView(R.id.shop_progress)
    ProgressBar shopProgress;
    @BindView(R.id.shop_content)
    TextView shopContent;
    @BindView(R.id.shop_layout)
    RelativeLayout showLayout;
    @BindView(R.id.shop_show)
    TextView showContent;

    public ShopAdapter adapter;
    public List<GoodsBean.SubGoodsBean> subGoodsBeanList = new ArrayList<>();
    public CommonPresenter commonPresenter;
    public SharedPreferencesManager sharedPreferencesManager;

    public String goodsType;
    public String areaString;
    public boolean isLoad = false;
    public int length = 0;
    public String shopName;

    @Override
    public void onRefresh() {
        length=0;
        isLoad=false;
        String[] values={"fetchShopGoods",areaString,goodsType,"0"};
        commonPresenter.fetchGoods(values);

    }

    @Override
    public int getLayout() {
        return R.layout.shop_activity;
    }

    @Override
    public void initInstance() {

        shopName=getIntent().getStringExtra("shopName");
        sharedPreferencesManager=SharedPreferencesManager.newInstance(this);
        areaString=sharedPreferencesManager.getPreferenceByAccount("area");
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recycler.setLayoutManager(new LinearLayoutManager(this));
        commonPresenter=new CommonPresenter();
        commonPresenter.setCommonHandleListener(this);
        commonPresenter.setModel("shopAction");
        recycler= ScrollManager.newInstance().setScrollListener(recycler,this);
        ScrollManager.newInstance().setListener(new ScrollManager.ScrollListener() {
            @Override
            public void scroll() {

                isLoad=true;
                String[] values={"fetchShopGoods",areaString,shopName,length+""};
                commonPresenter.fetchShopGoods(values);

            }
        });
    }

    @Override
    public void initData() {

        String[] values={"fetchShopGoods",areaString,shopName,"0"};
        commonPresenter.fetchShopGoods(values);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void begin() {
        showLayout.setVisibility(View.VISIBLE);
        showContent.setText("加载中...");
    }

    @Override
    public void success(Object object) {
        GoodsBean newsBean = ((GoodsBean) object);
        showLayout.setVisibility(View.GONE);
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
            adapter=new ShopAdapter(this,R.layout.goods_item,this.subGoodsBeanList);
            recycler.setAdapter(adapter);
            setListener(adapter);

        }else{
            ToastManager.show(this,"暂无更多商品数据");
        }
    }

    @Override
    public void fail(Object object) {
        ToastManager.show(this,"服务器出小差了");
    }

    public  void setListener(ShopAdapter adapter){

        adapter.setListener(new AdapterClickListener() {
            @Override
            public void handleClick(String[] values) {

            }
        });
    }
}
