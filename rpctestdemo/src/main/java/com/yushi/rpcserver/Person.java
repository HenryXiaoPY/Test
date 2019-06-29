package com.yushi.rpcserver;

/**
 * @Author: xiaolong
 * @Date: 2019/6/12 17:43
 */
public interface Person {
    //定义说话的方法
    String sayHello(String content);
    //定义跑步的方法
    String run(String content);
}
