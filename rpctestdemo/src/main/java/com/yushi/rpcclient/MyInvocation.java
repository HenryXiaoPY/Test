package com.yushi.rpcclient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author: xiaolong
 * @Date: 2019/6/28 14:29
 */
public class MyInvocation implements InvocationHandler {
    private InetSocketAddress address;
    private Class serviceInterface;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Object result;

    MyInvocation(InetSocketAddress address,Class serviceInterface){
        this.address = address;
        this.serviceInterface = serviceInterface;
    }

    //proxy:代理的对象，method:代理对象的方法，args:方法的参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IOException {
        try {
            Socket socket = new Socket();
            //通过socketaddress连接ip+端口
            socket.connect(address);
            output = new ObjectOutputStream(socket.getOutputStream());
            //发送接口名，方法名
            output.writeUTF(serviceInterface.getName());
            output.writeUTF(method.getName());
            //发送参数类型，参数
            output.writeObject(method.getParameterTypes());
            System.out.println("写入方法参数类型时报错");
            output.writeObject(args);
            //等待服务端处理请求数据
            //服务端处理完成返回数据进行接收
            input = new ObjectInputStream(socket.getInputStream());
            this.result = input.readObject();
            return input.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (output!=null)output.close();
            if (input!=null)input.close();
        }
        return result;
    }
}
