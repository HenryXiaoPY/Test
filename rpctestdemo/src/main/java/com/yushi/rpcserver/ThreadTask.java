package com.yushi.rpcserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

/**
 * @Author: xiaolong
 * @Date: 2019/6/28 10:12
 */
public class ThreadTask implements Runnable {

    private Socket socket;
    private HashMap<String, Class> serverRegister;


    ThreadTask(Socket socket, HashMap<String, Class> serverRegister) {
        this.socket = socket;
        this.serverRegister = serverRegister;
    }

    @Override
    public void run() {
        ObjectOutputStream outputStream = null;
        ObjectInputStream input = null;
        try {

            System.out.println("接收到一个客户端请求...");
            //接收客户端请求数据
            input = new ObjectInputStream(socket.getInputStream());
            input.readObject();
            String serviceName = input.readUTF();
            String methodName = input.readUTF();
            System.out.println("服务端读取到的数据:"+serviceName+" "+methodName);
            Class[] parameterType = (Class[]) input.readObject();
            Class[] argument = (Class[]) input.readObject();
            //通过hashmap获取接口名
            Class service = serverRegister.get(serviceName);
            //通过接口获取到方法
            Method method = service.getMethod(methodName, parameterType);
            //通过反射获取到方法的返回结果
            Object result = method.invoke(service.newInstance(), argument);

            //通过服务端的返回结果，发送给客户端
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject("hello:");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

