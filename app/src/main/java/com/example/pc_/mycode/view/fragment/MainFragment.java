package com.example.pc_.mycode.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc_.mycode.R;
import com.example.pc_.mycode.model.bean.GoodShopBean;
import com.example.pc_.mycode.myinterface.commonListener.AdapterClickListener;
import com.example.pc_.mycode.myinterface.commonListener.CommonHandleListener;
import com.example.pc_.mycode.presenter.CommonPresenter;
import com.example.pc_.mycode.utils.ScrollManager;
import com.example.pc_.mycode.utils.SharedPreferencesManager;
import com.example.pc_.mycode.utils.ToastManager;
import com.example.pc_.mycode.view.MyApplication;
import com.example.pc_.mycode.view.adapter.MainAdapter;
import com.example.pc_.mycode.view.custom.CustomImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/13.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener , SwipeRefreshLayout.OnRefreshListener,CommonHandleListener {

    @BindView(R.id.main_greens)
    CustomImageView mainGreens;
    @BindView(R.id.main_cooked)
    CustomImageView mainCooked;
    @BindView(R.id.main_seaFood)
    CustomImageView mainSeaFood;
    @BindView(R.id.main_freeze)
    CustomImageView mainFreeze;
    @BindView(R.id.main_goods)
    CustomImageView mainGoods;
    @BindView(R.id.main_grains)
    CustomImageView mainGrains;
    @BindView(R.id.main_fruit)
    CustomImageView mainFruit;
    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;
    @BindView(R.id.main_fragment_layout)
    RelativeLayout relativeLayout;
    @BindView(R.id.main_fragment_content)
    TextView textContent;
    @BindView(R.id.main_fragment_show)
    TextView  showText;
    @BindView(R.id.main_meat)
    CustomImageView mainMeat;
    @BindView(R.id.fragment_SwipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    public MainAdapter mainAdapter;
    public Intent intent;
    public MyApplication myApplication;
    public SharedPreferencesManager sharedPreferencesManager;
    public List<GoodShopBean.SubGoodShopBean> subGoodShopBeans=new ArrayList<>();
    public CommonPresenter commonPresenter;


    public String areaString;
    public String intentArea;
    public String[] values=new String[4];
    public int start=0;
    public int  length=12;
    public boolean  isRefreshing=false;
    public boolean  isLoad=false;

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_meat:
                startToActivity(0);
                break;
            case R.id.main_greens:
                startToActivity(2);
                break;
            case R.id.main_cooked:
                startToActivity(3);
                break;
            case R.id.main_seaFood:
                startToActivity(4);
                break;
            case R.id.main_freeze:
                startToActivity(5);
                break;
            case R.id.main_goods:
                startToActivity(6);
                break;
            case R.id.main_grains:
                startToActivity(7);
                break;
            case R.id.main_fruit:
                startToActivity(8);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void initInstance() {
        sharedPreferencesManager=SharedPreferencesManager.newInstance(getActivity());
        areaString=sharedPreferencesManager.getPreferenceByAccount("area");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mainRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonPresenter=new CommonPresenter();
        commonPresenter.setCommonHandleListener(this);
        commonPresenter.setModel("shopAction");

    }

    @Override
    public void initData() {
        String[] values={"fetchGoodShop",areaString,"0"};
        commonPresenter.fetchGoodShop(values);

    }

    @Override
    public void initEvent() {
        mainMeat.setOnClickListener(this);
        mainGreens.setOnClickListener(this);
        mainCooked.setOnClickListener(this);
        mainSeaFood.setOnClickListener(this);
        mainFreeze.setOnClickListener(this);
        mainGoods.setOnClickListener(this);
        mainGrains.setOnClickListener(this);
        mainFruit.setOnClickListener(this);
        mainRecycler= ScrollManager.newInstance().setScrollListener(mainRecycler,getActivity());
        ScrollManager.newInstance().setListener(new ScrollManager.ScrollListener() {
            @Override
            public void scroll() {

                isLoad=true;
                String[] values={"fetchGoodShop",areaString,length+""};
                commonPresenter.fetchGoodShop(values);

            }
        });


    }

    public void startToActivity(int i){

        if(i==0){
            selectType("肉类");
        }else if(i==2){
            selectType("蔬菜");
        }else if(i==3){
            selectType("熟食");
        }else if(i==4){
            selectType("海鲜");
        }else if(i==5){
            selectType("冻品");
        }else if(i==6){
            selectType("杂货");
        }else if(i==7){
            selectType("杂粮");
        }else if(i==8){
            selectType("果类");
        }
        startActivity(intent);
    }
    public void  selectType(String goodsType){
//        intent=new Intent(getActivity(), GoodsActivity.class);
//        intent.putExtra("GoodsType",goodsType);
    }

    @Override
    public void begin() {
        relativeLayout.setVisibility(View.VISIBLE);
        textContent.setText("获取中...");
    }

    @Override
    public void success(Object object) {
        GoodShopBean newsBean = ((GoodShopBean) object);
        relativeLayout.setVisibility(View.GONE);
        if(newsBean.getTotal()>0){
            if(isLoad){
                length=newsBean.getShopBenas().size()+length;
                for(int i=0;i<newsBean.getShopBenas().size();i++){
                    this.subGoodShopBeans.add(newsBean.getShopBenas().get(i));
                }
            }else{
                length=newsBean.getShopBenas().size();
                this.subGoodShopBeans=newsBean.getShopBenas();
            }
            mainAdapter=new MainAdapter(getActivity(),R.layout.main_item,this.subGoodShopBeans);
            mainRecycler.setAdapter(mainAdapter);
            setListener(mainAdapter);

        }else{
            ToastManager.show(getActivity(),"暂无更多商店数据");
        }

    }

    @Override
    public void fail(Object object) {
        ToastManager.show(getActivity(),"服务器累了");
    }

    public void setListener(MainAdapter mainAdapter){
        mainAdapter.setListener(new AdapterClickListener() {
            @Override
            public void handleClick(String[] values) {
//                intent=new Intent(getActivity(), ShopActivity.class);
//                intent.putExtra("shopName",values[0]);
//                getActivity().startActivity(intent);
            }
        });
    }
}
