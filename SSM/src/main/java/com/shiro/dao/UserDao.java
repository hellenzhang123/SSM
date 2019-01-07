package com.shiro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shiro.bean.User;

public interface UserDao {
	List<User> findAll(); 
	User findOne(String name);
}
