package com.shiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.bean.User;
import com.shiro.dao.UserDao;

@Controller
public class UserController {

	@Autowired
	UserDao userDao;
	@RequestMapping("/user")
	@ResponseBody
	public User getUser(String name) {
		return userDao.findOne(name);
	}
}
