package com.example.pc_.mycode.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc_.mycode.R;
import com.example.pc_.mycode.model.bean.GoodsBean;
import com.example.pc_.mycode.myinterface.commonListener.AdapterClickListener;
import com.example.pc_.mycode.utils.GlideDownload;

import java.util.List;


/**
 * Created by pc- on 2017/8/13.
 */
public class ShopAdapter extends BaseQuickAdapter<GoodsBean.SubGoodsBean> {



    private Context context;
    private float goodsMoney;
    public ShopAdapter(Context context, int layoutResId, List<GoodsBean.SubGoodsBean> data) {
        this(layoutResId, data);
        this.context=context;
    }

    private ShopAdapter(int layoutResId, List<GoodsBean.SubGoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final GoodsBean.SubGoodsBean meatModel) {


        if(!meatModel.getMark().equals("暂无评分")){
            baseViewHolder.setText(R.id.item_shop_grade,meatModel.getMark()+"分");
        }
        baseViewHolder.setText(R.id.item_shop_name,meatModel.getGoods_name())
                .setText(R.id.item_shop_sales,"月售"+meatModel.getSales()+"份")
                .setText(R.id.item_shop_type,meatModel.getType())
                .setText(R.id.shop_danJia,meatModel.getPrice()+"元/斤")
                .setText(R.id.shop_buy,"买"+meatModel.getDan());

        ImageView imageView=( baseViewHolder.getView(R.id.item_shop_image));
        if(meatModel.getUrl()!=null&&!meatModel.getUrl().equals("0")){
            GlideDownload.newInstance().download((Activity) context,meatModel.getUrl(),imageView);
        }else{
            imageView.setImageResource(R.drawable.meat);
        }
        baseViewHolder.getView(R.id.shop_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(meatModel.getDan().contains("半斤")){

                    goodsMoney=Float.valueOf(meatModel.getPrice())/2;
                }else{

                    goodsMoney=Float.valueOf(meatModel.getPrice());
                }

                int shopNameLength=meatModel.getShopName().length();
                int shopNameBoth=9-shopNameLength;
                String space="";
                for(int i=0;i<shopNameBoth;i++){
                    space+="....";
                }
                String[] values={meatModel.getShopName(),meatModel.getGoods_name(),meatModel.getType(),meatModel.getDan(),meatModel.getUrl(),goodsMoney+""};
                adapterClickListener.handleClick(values);
            }
        });
    }


    private AdapterClickListener adapterClickListener;
    public void  setListener(AdapterClickListener adapterClickListener){
        this.adapterClickListener=adapterClickListener;
    }
}
