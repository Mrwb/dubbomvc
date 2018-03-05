package com.myswing.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myswing.api.DemoService;
import com.myswing.api.UserService;
import com.myswing.api.VersionUpgradeService;

/**
 * Created by ken.lj on 2017/7/31.
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/dubbo-consumer.xml"});
        context.start();

//        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
//        String hello = demoService.sayHello("world"); // 执行远程方法
        UserService userService = (UserService) context.getBean("userService"); // 获取远程服务代理
        String result = userService.queryUser(); // 执行远程方法
        System.out.println(result); // 显示调用结果
    }
}
