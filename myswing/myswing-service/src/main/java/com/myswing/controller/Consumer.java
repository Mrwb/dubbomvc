package com.myswing.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myswing.api.DemoService;
import com.myswing.api.VersionUpgradeService;

/**
 * Created by ken.lj on 2017/7/31.
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-consumer.xml"});
        context.start();

//        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
//        String hello = demoService.sayHello("world"); // 执行远程方法
        VersionUpgradeService versionUpgradeService = (VersionUpgradeService) context.getBean("versionUpgradeService"); // 获取远程服务代理
        String hello = versionUpgradeService.checkVersion("1"); // 执行远程方法
        System.out.println(hello); // 显示调用结果
    }
}