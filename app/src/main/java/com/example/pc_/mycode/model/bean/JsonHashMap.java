package com.example.pc_.mycode.model.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc- on 2017/10/11.
 */

public class JsonHashMap {

    public List<Map<String, String>> getHashMap() {
        return hashMap;
    }

    public void setHashMap(List<Map<String, String>> hashMap) {
        this.hashMap = hashMap;
    }

    public List<Map<String,String>>  hashMap=new ArrayList<>();

    public JsonHashMap(String[] columns, String[] values){

         for(int i=0;i<columns.length;i++){
             Map<String ,String>  map=new HashMap<>();
             map.put(columns[i],values[i]);
             hashMap.add(map);
         }
    }
}
