package com.cnsyear.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
/**
 * �Զ�����֤
 * @author jiebaobao
 *
 */
public class ShiroRealmSecond extends AuthenticatingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//���ԡ�����
		System.out.println("22222222222222222 doGetAuthenticationInfo...."+token.hashCode());
		//1.��AuthenticationTokenת����UsernamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		//2.��UsernamePasswordToken��ȡ��username
		String username = usernamePasswordToken.getUsername();
		//3.����Ӧ�õ������ݿ���в�ѯusername��Ӧ������  ����ģ��һ��
		System.out.println("�����ݿ��л�ȡ username: " + username + " ����Ӧ���û���Ϣ.");
		//4.����ģ�����һ�� �û������ڵ��쳣  UnknownAccountException 
		if("unknown".equals(username)){
			throw new UnknownAccountException("�û�������!");
		}
		//5. ����ģ�����һ��  �����û���Ϣ�����, �����Ƿ���Ҫ�׳������� AuthenticationException �쳣. 
		if("lock".equals(username)){
			throw new LockedAccountException("�û�������");
		}
		//6. �����û������, ������ AuthenticationInfo ���󲢷���. ͨ��ʹ�õ�ʵ����Ϊ: SimpleAuthenticationInfo
		//������Ϣ�Ǵ����ݿ��л�ȡ��.
		//1). principal: ��֤��ʵ����Ϣ. ������ username, Ҳ���������ݱ��Ӧ���û���ʵ�������. 
		Object principal = username;
		//2). credentials: ����. 
		Object credentials = "";//ģ�����
		//ģ����β��� 
		if("admin".equals(username)) {
			Object result = new SimpleHash("SHA1", "123456", ByteSource.Util.bytes(username), 1024);
			
			credentials = result.toString();
		}
		
		//3). realmName: ��ǰ realm ����� name. ���ø���� getName() ��������
		String realmName = getName();
		// 4).��ֵ
		ByteSource salt = ByteSource.Util.bytes(username);//����ʹ���û�������ֵ��������һ�����������
		SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials,salt, realmName);
		return info;
	}
	
}
