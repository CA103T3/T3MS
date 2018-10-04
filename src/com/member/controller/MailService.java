package com.member.controller;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import redis.clients.jedis.Jedis;
public class MailService {
	
	
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {
				
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ixlogic.wu@gmail.com";
		     final String myGmail_password = "BBB45678";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
		
		 public void setmail (String memno, String name ,String email){

	      String to = email;
	      
	      String subject = "MS會員註冊驗證";
	      
	      String ch_name = name;

	      	Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
			
			String code = returnAuthCode();
			System.out.println("Auth code is: " + code);
			
			jedis.set(memno, code);
			jedis.expire(memno, 120);
			
			String messageText = "Hello! " + ch_name +  "\n" +" 驗證碼:"+code; 
			
			// 假設會員點擊驗證信
			String tempAuth = jedis.get(memno);
			
			jedis.close();
		
	      
	      MailService mailService = new MailService();
	      mailService.sendMail(to, subject, messageText);
		 }
		 
		 private static String returnAuthCode() {
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= 8; i++) {
					int condition = (int) (Math.random() * 3) + 1;
					switch (condition) {
					case 1:
						char c1 = (char)((int)(Math.random() * 26) + 65);
						sb.append(c1);
						break;
					case 2:
						char c2 = (char)((int)(Math.random() * 26) + 97);
						sb.append(c2);
						break;
					case 3:
						sb.append((int)(Math.random() * 10));
					}
				}
				return sb.toString();
			}
}


