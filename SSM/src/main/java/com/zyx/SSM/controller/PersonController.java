package com.zyx.SSM.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyx.SSM.bean.Person;
import com.zyx.SSM.service.PersonService;
 
 
@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/main")
	public String test(){
		
		List<Person> list = personService.findAll();
		for (Person person : list) {
			System.out.println(person);
		}
		return "main";
	}
	@RequestMapping("/test")

	public String testOne(@RequestParam("username") String username, @RequestParam("password") String password){
		System.out.println(username);
		System.out.println(password);
		if(username.equals("tom")&&password.equals("123")) {
			return "success";
		}
		return "unauthorized";
	}

}
