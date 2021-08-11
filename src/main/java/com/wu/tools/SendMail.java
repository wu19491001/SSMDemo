package com.wu.tools;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @author Administrator
 *发送邮件
 */
public class SendMail{
	public static void sendmail(String msg,String toemail) throws GeneralSecurityException {
    //发件人信息
	String from="水管官方邮件";
	//发件人邮箱
	final String recipient="255099423@qq.com";
	//发件人授权码-ikbmhbyqfccwbgic
	final String password="ikbmhbyqfccwbgic";
	//邮件服务器
	String host="smtp.qq.com";
		// 获取系统属性
	Properties pro=System.getProperties();
	pro.setProperty("mail.smtp.host",host);
	pro.put("mail.smtp.auth", "true");
    MailSSLSocketFactory sf = new MailSSLSocketFactory();
    sf.setTrustAllHosts(true);
    pro.put("mail.smtp.ssl.enable", "true");
    pro.put("mail.smtp.ssl.socketFactory", sf);
	// 获取默认的 Session 对象。
    Session session = Session.getDefaultInstance(pro,new Authenticator() {
	  public PasswordAuthentication getPasswordAuthentication() {
		  return new PasswordAuthentication(recipient, password);
	  }
    });

    try{
       // 创建默认的 MimeMessage 对象。
       MimeMessage message = new MimeMessage(session);

       // Set From: 头部头字段
       message.setFrom(new InternetAddress(recipient));

       // Set To: 头部头字段
       message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(toemail));

       // Set Subject: 头字段
       message.setSubject(from);

       // 发送 HTML 消息, 可以插入html标签
       message.setContent("您的验证码为"+"<h1>"+msg+"</h1>",
                          "text/html;charset=UTF-8" );

       // 发送消息
       Transport.send(message);
       System.out.println("Sent message successfully....");
    }catch (MessagingException mex) {
       mex.printStackTrace();
    }
	
	}	
}
