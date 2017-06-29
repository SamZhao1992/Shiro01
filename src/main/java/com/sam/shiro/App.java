package com.sam.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {

   private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("begin>>>>>>>>");
        //读取配置文件    初始化SecurityManager 工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取SecurityManager 实例
        SecurityManager securityManager = factory.getInstance();
        //把securityManager 实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //得到当前执行的用户
        Subject currentUser = SecurityUtils.getSubject();
        //创建token令牌，用户名/密码 形式
        UsernamePasswordToken token = new UsernamePasswordToken("java1234", "123456");

        try{
            //登录(身份认证)
            currentUser.login(token);
            logger.info("success!!!");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("fail!!!");
        }finally {
            currentUser.logout();
            logger.info("end<<<<<<<<");
        }


    }

}
