package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * @Author luofeng
 * @Date 2022/6/2
 **/
public class MyLoadBalance implements LoadBalance {
    /**
     * select one invoker in list.
     *
     * @param invokers   invokers.
     * @param url        refer url
     * @param invocation invocation.
     * @return selected invoker.
     */
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        System.out.println("use MyLoadBalance");
        return invokers.get(0);
    }
}
