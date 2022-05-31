/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.store.DataStore;
import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.fastjson.JSON;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        //设置jvm属性，禁用IPV6
        System.setProperty("java.net.preferIPv4Stack", "true");
        //通过ClassPathXmlApplicationContext创建BeanFactory，加载xml文件中的bean
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        //启动spring容器
        context.start();

        /*
        while (true) {
            try {
                Thread.sleep(1000);
                //获取远程服务代理
                DemoService demoService = (DemoService) context.getBean("demoService");
                //调用远程服务方法
                String hello = demoService.sayHello("world");
                System.out.println(hello);

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }*/

        ExtensionLoader<DataStore> extensionLoader = ExtensionLoader.getExtensionLoader(DataStore.class);
        DataStore myDataStore = extensionLoader.getExtension("myDataStore");
        myDataStore.put("test","name","luofeng");
        String value = (String) myDataStore.get("test","name");
        System.out.println(value);

    }
}
