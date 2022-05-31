package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.common.store.DataStore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author luofeng
 * @Date 2022/5/30
 **/
public class MyDataStore implements DataStore {

    private ConcurrentMap<String, ConcurrentMap<String, Object>> data =
            new ConcurrentHashMap<String, ConcurrentMap<String, Object>>();

    @Override
    public Map<String, Object> get(String componentName) {
        System.out.println("myDataStore get:" + componentName);
        ConcurrentMap<String, Object> value = data.get(componentName);
        if (value == null) {
            return new HashMap<String, Object>();
        }
        return new HashMap<String, Object>(value);
    }

    @Override
    public Object get(String componentName, String key) {
        System.out.println("myDataStore get:" + componentName + "-" + key);
        if (!data.containsKey(componentName)) {
            return null;
        }
        return data.get(componentName).get(key);
    }

    @Override
    public void put(String componentName, String key, Object value) {
        System.out.println("myDataStore put:" + componentName + "-" + key);
        ConcurrentMap<String, Object> componentData = data.get(componentName);
        if (null == componentData) {
            data.putIfAbsent(componentName, new ConcurrentHashMap<String, Object>());
            componentData = data.get(componentName);
        }
        componentData.putIfAbsent(key, value);
    }

    @Override
    public void remove(String componentName, String key) {
        System.out.println("myDataStore remove:");
        if (!data.containsKey(componentName)) {
            return;
        }
        data.get(componentName).remove(key);
    }
}
