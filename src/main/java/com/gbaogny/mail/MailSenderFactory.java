package com.gbaogny.mail;

/**
 * 发件箱工厂
 * */
public class MailSenderFactory {
	
	private static SimpleMailSender serviceSms = null;
	
	public static SimpleMailSender getSender(MailSenderType type){
		if(type == MailSenderType.SERVICE){
			if(serviceSms == null){
				serviceSms = new SimpleMailSender("invisible@126.com", "hidden");
			}
			return serviceSms;
		}
		return null;
	}
}
