package com.yushi.rpcserver;

/**
 * @Author: xiaolong
 * @Date: 2019/6/12 17:46
 */
public class SayHello implements Person{
//    private String content;
//
//    public SayHello(String content){
//        this.content = content;
//    }


    @Override
    public String sayHello(String content) {
        return "调用sayHello方法:"+content;
    }

    @Override
    public String run(String content) {
        return "调用run方法:"+content;
    }
}
