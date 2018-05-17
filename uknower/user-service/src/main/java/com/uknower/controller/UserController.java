package com.uknower.controller;

import com.uknower.model.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uknower.command.UserCommand;

/**
 * @version 1.1
 * @author Eric.wang
 * @date 2018/03/08 14:48
 * @email 1595905476(a)qq.com
 */

@Controller
@RequestMapping("/User")
public class UserController extends BaseController{
	@RequestMapping("/query")
	@ResponseBody
	public JSON query(){
		UserCommand command = (UserCommand) beanFactory.getBean("userCommand");
		JSON json = command.execute();
		return json;
	}
}