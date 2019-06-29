package com.yushi.rpcclient;

import com.yushi.rpcserver.SayHello;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * @Author: xiaolong
 * @Date: 2019/6/13 8:12
 */
public class Client{
    /**
     * 1.创建一个动态代理对象
     * 2.传入接口，ip地址，端口
     */
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress address){

        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),new Class<?>[]{serviceInterface},
        new MyInvocation(address,serviceInterface));
    }
}
