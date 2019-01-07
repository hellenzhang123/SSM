package com.zyx.SSM.javase;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * shiro 认证用户
 * @author 张雨轩
 *
 */
public class HelloWorld {
	private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);
	public static void main(String[] args) {
		log.info("测试log");
		/**
		 * 1. 获取安全管理器
		 * 2. 获取用户
		 * 3. 用户登录验证
		 * 4. 权限管理
		 * 5. 角色管理
		 * 6. session:用户登录到用户退出，作用域
		 */
		//1. 获取安全管理器
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2. 获取securityManager
		SecurityManager securityManager = factory.getInstance();
		//3. 需要设置安全管理器
		SecurityUtils.setSecurityManager(securityManager);
		//4. 获取subject对象，即将登录的对象
		Subject subject = SecurityUtils.getSubject();
		//5. 获取session
		Session session = subject.getSession();
		session.setAttribute("name", "酸酱");
		String value = (String) session.getAttribute("name");
		if (value != null) {
			log.info("拿到session中的name" + value);
		}
		if(subject.isAuthenticated() == false) {
			//UsernamePasswordToken 登录
			UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
			token.setRememberMe(true);
			try {
				subject.login(token);
				log.info("用户名密码正确，登录成功啦");
			} catch (UnknownAccountException e) {
				log.error("账户不存在");
			} catch(IncorrectCredentialsException e) {
				log.error("密码错误");
			} catch(LockedAccountException e) {
				log.error("用户已经锁死");
			} catch (AuthenticationException e) {
				log.error("认证异常");
			}
			//判断用户是否具有特定的角色
			if (subject.hasRole("kk")) {
				log.info("拥有指定的角色");
			} else {
				log.info("不具有指定的角色");
			}
			//判断用户是否有指定参数的权限
			if (subject.isPermitted("edit")) {
				log.info("拥有指定的权限");
			} else {
				log.info("不拥有指定的权限");
			}
			//退出登录
			subject.logout();
		}
	}
}
