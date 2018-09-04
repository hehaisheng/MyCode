package com.example.pc_.mycode.utils;

import android.content.Context;

import com.example.pc_.mycode.database.OrderItem;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

import java.util.List;

/**
 * Created by pc- on 2017/7/9.
 */
public class LiteOrmManager {


    public  Context context;
    public LiteOrm liteOrm;

    public static  LiteOrmManager liteOrmManager;
    public static  LiteOrmManager newInstance(Context context){
        if(liteOrmManager==null){
            synchronized (LiteOrmManager.class){
                if(liteOrmManager==null){
                    liteOrmManager=new LiteOrmManager(context);
                }

            }
        }
        return liteOrmManager;
    }
    public LiteOrmManager(Context context){
        DataBaseConfig config = new DataBaseConfig(context.getApplicationContext(), "liteOrm.db");
        config.dbVersion = 1;
        config.onUpdateListener = null;
        liteOrm = LiteOrm.newSingleInstance(config);
    }



    public void save(OrderItem orderItem){
        liteOrm.save(orderItem);
    }
    public void update(OrderItem orderItem){
       liteOrm.update(orderItem);
   }
    public void delete(OrderItem orderItem){liteOrm.delete(orderItem);}
    public <T> List<T> getQueryAll(Class<T> cla) {
        return liteOrm.query(cla);
    }
    public void deleteOrderItem(OrderItem orderItem){
        liteOrm.delete(orderItem.getClass());
    }

    public void saveObject(Object object){
        liteOrm.save(object);
    }
    public void updateObject(Object object){
        liteOrm.update(object);
    }
    public void deleteObject(Object object){
        liteOrm.delete(object);
    }






}
