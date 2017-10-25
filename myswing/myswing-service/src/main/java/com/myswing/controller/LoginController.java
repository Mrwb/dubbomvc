package com.myswing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myswing.api.VersionUpgradeService;

/**
 * @version 1.0
 * @author wangbo
 * @date 2017-10-25 16:40
 * @email 1595905476(a)qq.com
 */
@Controller
public class LoginController{
	@Autowired
	VersionUpgradeService versionUpgradeService;
	@RequestMapping("/index")
	public String main(Model model){
        String hello = versionUpgradeService.checkVersion("1"); // 执行远程方法
        System.out.println(hello); // 显示调用结果
		return "/index";
	}
}