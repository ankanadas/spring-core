package com.starter.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
@RequestMapping(value="/welcome")	
public String display(){
	return "Welcome";
}
}
