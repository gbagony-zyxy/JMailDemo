package com.gbaogny.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SimpleMailSender {
	
	private Properties props = System.getProperties();
	
	private transient MailAuthenticator mailAuthenticator;
	
	private transient Session session;
	
	/**
	 * 初始化邮件发送器
	 * */
	public SimpleMailSender(String smptHostName,String username,String password) {
		init(smptHostName,username,password);
	}
	
	/**
	 * 初始化邮件发送器
	 * */
	public SimpleMailSender(String username,String password){
		String smptHostName = "smpt"+username.split("@")[1];
		init(smptHostName, username, password);
	}
	
	private void init(String smptHostName, String username, String password) {
		props.put("mail.smpt.auth", "true");
		props.put("mail.smpt.host", smptHostName);
		
		//验证
		mailAuthenticator = new MailAuthenticator(username, password);
		//创建session
		session=Session.getInstance(props,mailAuthenticator);
		
	}
	
	/**
	 * 发送邮件
	 * */
	public void send(String recipient,String subject,Object content) throws AddressException, MessagingException{
		//创建mime类型邮件
		MimeMessage message = new MimeMessage(session);
		//设置发信人
		message.setFrom(new InternetAddress(mailAuthenticator.getUsername()));
		//设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		//设置主题
		message.setSubject(subject);
		//设置邮件内容
		message.setContent(content, "text/html;charset=utf-8");
		//发送
		Transport.send(message);
	}
	
	/**
	 * 群发邮件
	 * @throws MessagingException 
	 * @throws AddressException 
	 * */
	public void send(List<String> recipients,String subject,Object content) throws AddressException, MessagingException{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailAuthenticator.getUsername()));
		int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for(int i=0;i<num;i++){
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(RecipientType.TO, addresses);
		message.setSubject(subject);
		message.setContent(content, "text/html;charset=utf-8");
		Transport.send(message);
	}
	
	/**
	 * 发送邮件
	 * */
	public void send(String recipient,SimpleMail mail) throws AddressException, MessagingException{
		send(recipient, mail.getSubject(), mail.getContent());
	}
	
	
}
