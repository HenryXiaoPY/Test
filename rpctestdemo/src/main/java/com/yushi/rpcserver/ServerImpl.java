package com.yushi.rpcserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: xiaolong
 * @Date: 2019/6/12 17:57
 */
public class ServerImpl implements Server {
    //定义一个端口号变量
    private int port;
    /**
     * 1.服务端所有可供客户端访问的接口，注册到map中
     * 2.key为接口的名字”Person" value为真正“person”的实现
     */
    private static HashMap<String,Class> serverRegister = new HashMap<String, Class>();
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //通过构造函数定义一个服务端的端口号
    public ServerImpl(int port){ this.port = port; }

    @Override
    public void start() throws IOException {
        //设置一个socket并绑定网络端口
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));

        System.out.println("开启服务。。。。");
        while (true) {
            try {
                //接收客户端请求
                Socket socket = serverSocket.accept();
                //执行多线程
                executor.execute(new ThreadTask(socket, serverRegister));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        System.out.println("关闭线程池!!!");
        executor.shutdown();
    }

    @Override
    public void register(Class service,Class serviceImpl) {
        serverRegister.put(service.getName(),serviceImpl);
    }
}
