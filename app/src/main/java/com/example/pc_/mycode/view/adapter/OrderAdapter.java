package com.example.pc_.mycode.view.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc_.mycode.R;
import com.example.pc_.mycode.database.OrderItem;
import com.example.pc_.mycode.utils.AutoSplit;
import com.example.pc_.mycode.utils.DateManager;
import com.example.pc_.mycode.utils.LiteOrmManager;

import java.util.List;

/**
 * Created by pc- on 2017/8/13.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderItem>  {

    public Context context;
    public LiteOrmManager liteOrmManager;
    private OrderItem orderItem;
    private Button myPayButton;
    private TextView myTextView;
    private TextView payMoneyText;

    private String timeString;

    public OrderAdapter(Context context, int layoutResId, List<OrderItem> data){
        this(layoutResId,data);
        this.context=context;
        liteOrmManager=LiteOrmManager.newInstance(context);
    }
    private OrderAdapter(int layoutResId, List<OrderItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final OrderItem orderItem) {
        baseViewHolder.setText(R.id.order_shop_name, AutoSplit.autoSplit(orderItem.getOrderStrings()))
                .setText(R.id.order_shopping_money,"应付"+orderItem.getMoney()+"元  ")
        .setText(R.id.order_shop_time,orderItem.getPayTime());
        this.payMoneyText=baseViewHolder.getView(R.id.order_shopping_send);
        if(orderItem.isHasPay()){
            baseViewHolder.setText(R.id.order_pay,"已支付");
            payMoneyText.setText("已结账");
        }


        this.orderItem=orderItem;
        final Button  payButton= baseViewHolder.getView(R.id.order_pay);
        final TextView payTimeView=baseViewHolder.getView(R.id.order_shop_time);
        myPayButton=payButton;
        myTextView=payTimeView;
        payButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String dateString= DateManager.newInstance().getSimpleDayDate();
                timeString=dateString;
                if(orderItem.isHasPay()){
                  //已经支付过
                    Toast.makeText(context,"已支付",Toast.LENGTH_SHORT).show();
               }else{

               }

            }
        });
    }

}
