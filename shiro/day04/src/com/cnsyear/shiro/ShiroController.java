package com.cnsyear.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ��¼������
 * @author jiebaobao
 *
 */
@RequestMapping("/shiro")
@Controller
public class ShiroController {

	@RequestMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		// 1.��ȡ��ǰ��Subject
		Subject currentUser = SecurityUtils.getSubject();
		// 2.���Ե�ǰ���û��Ƿ��Ѿ�����֤. ���Ƿ��Ѿ���¼.
		if (!currentUser.isAuthenticated()) {
			// 3.���û����������װ��UsernamePasswordToken����
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// remember me
			token.setRememberMe(true);
			// 4.ִ�е�¼
			try {
				System.out.println("111:"+token.hashCode());
				currentUser.login(token);
				
			 } 
   
            // ������֤ʱ�쳣�ĸ���. 
            catch (AuthenticationException e) {
                //unexpected condition?  error?
            	System.out.println("��¼ʧ��");
            }
		}

		// ��¼�ɹ�
		return "redirect:/index";
	}
}
