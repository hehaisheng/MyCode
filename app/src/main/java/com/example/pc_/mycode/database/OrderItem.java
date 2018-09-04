package com.example.pc_.mycode.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by pc- on 2017/8/14.
 */
@Table("OrderItem")
public class OrderItem {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    public String getShopNames() {
        return shopNames;
    }

    public void setShopNames(String shopNames) {
        if(shopNames.equals("")){
            this.shopNames="";
        }else{
            if(this.shopNames==null){
                this.shopNames=shopNames;
            }else{
                this.shopNames+= shopNames;
            }
        }

    }

    @Column("shopNames")
    public String shopNames;

    public String getGoodsNames() {
        return goodsNames;
    }

    public void setGoodsNames(String goodsNames) {
        if(goodsNames.equals("")){
            this.goodsNames="";
        }else{
            if(this.goodsNames==null){
                this.goodsNames=goodsNames;
            }else{
                this.goodsNames+= goodsNames;
            }
        }

    }

    @Column("goodsNames")
    public String  goodsNames;
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        if(goodsType.equals("")){
            this.goodsType="";
        }else{
            if(this.goodsType==null){
                this.goodsType=goodsType;
            }else{
                this.goodsType+= goodsType;
            }
        }

    }

    @Column("goodsType")
    public  String  goodsType;

    public String getOrderStrings() {
        return orderStrings;
    }

    public void setOrderStrings(String orderStrings) {

        if(orderStrings.equals("")){
            this.orderStrings="";
        }else{
            if(this.orderStrings==null){
                this.orderStrings=orderStrings;
            }else{
                this.orderStrings+= orderStrings;
            }
        }


    }

    @Column("OrderStrings")
    public String  orderStrings;

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    @Column("PayTime")
    public String  payTime="0";

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        if(money==0){
            this.money=0;
        }else{
            this.money+= money;
        }

    }

    @Column("PayMoney")
    public float money;

    public boolean isHasPay() {
        return hasPay;
    }

    public void setHasPay(boolean hasPay) {
        this.hasPay = hasPay;
    }

    @Column("HasPay")
    public boolean hasPay=false;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    @Column("PayType")
    public int payType=0;

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        if(orderTime.equals("")){
            this.orderTime="";
        }else{
            if(this.orderTime==null){
                this.orderTime=orderTime;
            }else{
                this.orderTime+=orderTime;
            }
        }
    }

    @Column("orderTime")
    private  String  orderTime;

    public String getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        if(goodsMoney.equals("")){
            this.goodsMoney="";
        }else{
            if(this.goodsMoney==null){
                this.goodsMoney=goodsMoney;
            }else{
                this.goodsMoney+=goodsMoney;
            }
        }
    }

    @Column("goodsMoney")
    private  String goodsMoney;


}
