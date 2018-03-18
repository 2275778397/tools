package com.cnsyear;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

        // The easiest way to create a Shiro SecurityManager with configured
        // realms, users, roles and permissions is to use the simple INI config.
        // We'll do that by using a factory that can ingest a .ini file and
        // return a SecurityManager instance:

        // Use the shiro.ini file at the root of the classpath
        // (file: and url: prefixes load from files and urls respectively):
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        // for this simple example quickstart, make the SecurityManager
        // accessible as a JVM singleton.  Most applications wouldn't do this
        // and instead rely on their container configuration or web.xml for
        // webapps.  That is outside the scope of this simple quickstart, so
        // we'll just do the bare minimum so you can continue to get a feel
        // for things.
        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        // get the currently executing user:
        // ��ȡ��ǰ�� Subject. ���� SecurityUtils.getSubject();
        Subject currentUser = SecurityUtils.getSubject();

        // Do some stuff with a Session (no need for a web or EJB container!!!)
        // ����ʹ�� Session 
        // ��ȡ Session: Subject#getSession()
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("---> Retrieved the correct value! [" + value + "]");
        }

        // let's login the current user so we can check against roles and permissions:
        // ���Ե�ǰ���û��Ƿ��Ѿ�����֤. ���Ƿ��Ѿ���¼. 
        // ���� Subject �� isAuthenticated() 
        if (!currentUser.isAuthenticated()) {
        	// ���û����������װΪ UsernamePasswordToken ����
        	// �û��� lonestarr ���� vespa
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            // rememberme
            token.setRememberMe(true);
            try {
            	// ִ�е�¼. 
                currentUser.login(token);
            } 
            // ��û��ָ�����˻�, �� shiro �����׳� UnknownAccountException �쳣. 
            catch (UnknownAccountException uae) {
                log.info("----> û��ָ�����˻�!  There is no user with username of " + token.getPrincipal());
                return; 
            } 
            // ���˻�����, �����벻ƥ��, �� shiro ���׳� IncorrectCredentialsException �쳣�� 
            catch (IncorrectCredentialsException ice) {
                log.info("----> �˻�����, �����벻ƥ��!  Password for account " + token.getPrincipal() + " was incorrect!");
                return; 
            } 
            // �û����������쳣 LockedAccountException
            catch (LockedAccountException lae) {
                log.info("�û����������쳣 ! The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // ������֤ʱ�쳣�ĸ���. 
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("----> User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        // �����Ƿ���ĳһ����ɫ. ���� Subject �� hasRole ����. 
        // ��ɫ schwartz
        if (currentUser.hasRole("schwartz")) {
            log.info("----> �иý�ɫ�� May the Schwartz be with you!");
        } else {
            log.info("----> û�иý�ɫ��  Hello, mere mortal.");
            return; 
        }

        //test a typed permission (not instance-level)
        // �����û��Ƿ�߱�ĳһ����Ϊ. ���� Subject �� isPermitted() ������ 
        if (currentUser.isPermitted("lightsaber:weild11231231")) {
            log.info("----> �и�Ȩ�ޣ� You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("----> û�и�Ȩ�ޣ� Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        // �����û��Ƿ�߱�ĳһ����Ϊ. 
        if (currentUser.isPermitted("user:delete:zhangsan")) {
            log.info("----> �и�Ȩ�ޣ�You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("----> û�и�Ȩ�ޣ� Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        //all done - log out!
        // ִ�еǳ�. ���� Subject �� Logout() ����. 
        System.out.println("---->" + currentUser.isAuthenticated());
        
        currentUser.logout();
        
        System.out.println("---->" + currentUser.isAuthenticated());

        System.exit(0);
    }
}
