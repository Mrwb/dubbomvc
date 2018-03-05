package com.myswing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.myswing.api.UserService;

/**
 * @version 1.0
 * @author Eric.wang
 * @date 2017/11/01 14:48
 * @email 1595905476(a)qq.com
 */

@Controller
public class UserController{
	@Autowired
	UserService userService;
	@RequestMapping("/User")
	public String User(Model model){
        String hello = userService.queryUser(); // 执行远程方法
        System.out.println(hello); // 显示调用结果
		return "/User";
	}
}