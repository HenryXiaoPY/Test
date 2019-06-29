package com.yushi.startserver;

import com.yushi.rpcserver.Person;
import com.yushi.rpcserver.ServerImpl;
import com.yushi.rpcserver.SayHello;
import com.yushi.rpcserver.Server;


/**
 * @Author: xiaolong
 * @Date: 2019/6/28 10:53
 */
public class RPCServerTest {
    public static void main(String[] args) {
        //创建服务中心实例
        Server server = new ServerImpl(9999);
        //将接口及接口实现添加到注册中心
        server.register(Person.class,SayHello.class);
        //启动注册中心服务
        try {
            server.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
