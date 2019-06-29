package com.yushi.rpcserver;


import java.io.IOException;

/**
 * @Author: xiaolong
 * @Date: 2019/6/12 18:06
 */
public interface Server {
    //服务启动
    void start() throws IOException;

    //服务关闭
    void  stop();

    void register(Class service,Class serviceImpl);

}
