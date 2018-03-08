package com.myswing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myswing.command.GetUserInfoCommand;

/**
 * @version 1.1
 * @author Eric.wang
 * @date 2018/03/08 14:48
 * @email 1595905476(a)qq.com
 */

@Controller
public class UserController extends BaseController{
	@RequestMapping("/User")
	public String User(Model model){
		GetUserInfoCommand command = (GetUserInfoCommand) beanFactory.getBean("getUserInfoCommand");
		String userinfo = command.execute();
		System.out.println(userinfo); 
		return "index";
	}
}