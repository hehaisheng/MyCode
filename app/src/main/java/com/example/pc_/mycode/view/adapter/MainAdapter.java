package com.example.pc_.mycode.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc_.mycode.R;
import com.example.pc_.mycode.model.bean.GoodShopBean;
import com.example.pc_.mycode.myinterface.commonListener.AdapterClickListener;
import com.example.pc_.mycode.utils.GlideDownload;

import java.util.List;

/**
 * Created by pc- on 2017/8/13.
 */
public class MainAdapter extends BaseQuickAdapter<GoodShopBean.SubGoodShopBean> {

    private Context context;
    private MainAdapter(int layoutResId, List<GoodShopBean.SubGoodShopBean> data) {
        super(layoutResId, data);
    }
    public MainAdapter(Context context,int layoutResId,  List<GoodShopBean.SubGoodShopBean> data) {
        this(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final GoodShopBean.SubGoodShopBean subGoodShopBean) {

        if(subGoodShopBean.getAllSales()==null){
            baseViewHolder.setText(R.id.item_main_shop_sales,"月售"+"0"+"份");
        }else{
            baseViewHolder.setText(R.id.item_main_shop_sales,"月售"+subGoodShopBean.getAllSales()+"份");
        }
        baseViewHolder.setText(R.id.item_main_shop_name,subGoodShopBean.getName())
                .setText(R.id.item_main_shop_mark,subGoodShopBean.getTotal()+"分")
                .setText(R.id.item_main_shop_type,subGoodShopBean.getType());

        ImageView imageView=((ImageView) baseViewHolder.getView(R.id.item_main_image));
        if(subGoodShopBean.getUrl()!=null&&!subGoodShopBean.getUrl().equals("0")){
            GlideDownload.newInstance().download((Activity) context,subGoodShopBean.getUrl(),imageView);
        }else{
            imageView.setImageResource(R.drawable.meat);
        }
        baseViewHolder.getView(R.id.item_main_relative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterClickListener.handleClick(new String[]{subGoodShopBean.getName()});

            }
        });
    }
    private AdapterClickListener adapterClickListener;
    public void  setListener(AdapterClickListener adapterClickListener){
        this.adapterClickListener=adapterClickListener;
    }

}
