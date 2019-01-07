package com.shiro.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dao.UserDao;
//Realm需要查询数据库获取安全数据
public class ShiroRealm extends AuthenticatingRealm {

	@Autowired
	UserDao userDao;
	/**
	 * 1. doGetAuthenticationInfo.获取认证消息，如果数据库没有查找到特定数据
	 * 应该返回null，如果得到正确的用户名和密码，返回指定类型的对象
	 * 2. AuthenticationInfo 可以使用simpleAuthenticationInfo实现类，给你正确的用户名和密码
	 * 3. token参数就是我们需要认证的token
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		SimpleAuthenticationInfo info = null;
		// 1. 将token转换成usernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		// 2. 从转换之后的token对象中获取用户名， 密码到数据库查询
		String username = upToken.getUsername();
		// 3. 查询数据库，是否 存在指定用户名和密码的用户
		// toDO:使用mybatis
		User user = userDao.findOne(username);
		if (user != null) {
			// 4. 如果查询到了，封装查询结果，并且返回给我们的调用
			Object principal = username;
			// 数据库中读出来的密码
			Object credentials = "";
			String realName = this.getName();
			// 获取数据，但是认证是shiro做的
			info = new SimpleAuthenticationInfo(principal, credentials, realName);
		}
		// 5. 如果没有查询到，抛出一个异常
		else {
			throw new AuthenticationException();
		}
		return null;
	}
	
}
