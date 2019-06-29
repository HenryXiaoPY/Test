package com.yushi.startserver;

import com.yushi.rpcclient.Client;
import com.yushi.rpcserver.Person;
import com.yushi.rpcserver.SayHello;
import java.net.InetSocketAddress;


/**
 * @Author: xiaolong
 * @Date: 2019/6/28 11:32
 */
public class RPCClientTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Person say = Client.getRemoteProxyObj(Class.forName("com.yushi.rpcserver.Person"),new InetSocketAddress("127.0.0.1",9999));
        System.out.println(say.getClass());
        System.out.println(say.sayHello("henry"));
    }
}
