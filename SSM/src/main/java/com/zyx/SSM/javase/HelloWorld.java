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
 * shiro ��֤�û�
 * @author ������
 *
 */
public class HelloWorld {
	private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);
	public static void main(String[] args) {
		log.info("����log");
		/**
		 * 1. ��ȡ��ȫ������
		 * 2. ��ȡ�û�
		 * 3. �û���¼��֤
		 * 4. Ȩ�޹���
		 * 5. ��ɫ����
		 * 6. session:�û���¼���û��˳���������
		 */
		//1. ��ȡ��ȫ������
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2. ��ȡsecurityManager
		SecurityManager securityManager = factory.getInstance();
		//3. ��Ҫ���ð�ȫ������
		SecurityUtils.setSecurityManager(securityManager);
		//4. ��ȡsubject���󣬼�����¼�Ķ���
		Subject subject = SecurityUtils.getSubject();
		//5. ��ȡsession
		Session session = subject.getSession();
		session.setAttribute("name", "�ὴ");
		String value = (String) session.getAttribute("name");
		if (value != null) {
			log.info("�õ�session�е�name" + value);
		}
		if(subject.isAuthenticated() == false) {
			//UsernamePasswordToken ��¼
			UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
			token.setRememberMe(true);
			try {
				subject.login(token);
				log.info("�û���������ȷ����¼�ɹ���");
			} catch (UnknownAccountException e) {
				log.error("�˻�������");
			} catch(IncorrectCredentialsException e) {
				log.error("�������");
			} catch(LockedAccountException e) {
				log.error("�û��Ѿ�����");
			} catch (AuthenticationException e) {
				log.error("��֤�쳣");
			}
			//�ж��û��Ƿ�����ض��Ľ�ɫ
			if (subject.hasRole("kk")) {
				log.info("ӵ��ָ���Ľ�ɫ");
			} else {
				log.info("������ָ���Ľ�ɫ");
			}
			//�ж��û��Ƿ���ָ��������Ȩ��
			if (subject.isPermitted("edit")) {
				log.info("ӵ��ָ����Ȩ��");
			} else {
				log.info("��ӵ��ָ����Ȩ��");
			}
			//�˳���¼
			subject.logout();
		}
	}
}
