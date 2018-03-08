package com.myswing.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	@Autowired
    BeanFactory beanFactory;
	
}
