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
//Realm��Ҫ��ѯ���ݿ��ȡ��ȫ����
public class ShiroRealm extends AuthenticatingRealm {

	@Autowired
	UserDao userDao;
	/**
	 * 1. doGetAuthenticationInfo.��ȡ��֤��Ϣ��������ݿ�û�в��ҵ��ض�����
	 * Ӧ�÷���null������õ���ȷ���û��������룬����ָ�����͵Ķ���
	 * 2. AuthenticationInfo ����ʹ��simpleAuthenticationInfoʵ���࣬������ȷ���û���������
	 * 3. token��������������Ҫ��֤��token
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		SimpleAuthenticationInfo info = null;
		// 1. ��tokenת����usernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		// 2. ��ת��֮���token�����л�ȡ�û����� ���뵽���ݿ��ѯ
		String username = upToken.getUsername();
		// 3. ��ѯ���ݿ⣬�Ƿ� ����ָ���û�����������û�
		// toDO:ʹ��mybatis
		User user = userDao.findOne(username);
		if (user != null) {
			// 4. �����ѯ���ˣ���װ��ѯ��������ҷ��ظ����ǵĵ���
			Object principal = username;
			// ���ݿ��ж�����������
			Object credentials = "";
			String realName = this.getName();
			// ��ȡ���ݣ�������֤��shiro����
			info = new SimpleAuthenticationInfo(principal, credentials, realName);
		}
		// 5. ���û�в�ѯ�����׳�һ���쳣
		else {
			throw new AuthenticationException();
		}
		return null;
	}
	
}
