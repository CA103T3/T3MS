package com.ticketorder.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class BACKUP_MailService2 {

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText, String name) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			
			MimeMultipart multipart = new MimeMultipart("related");
			
			 BodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setContent(name, "text/plain");
	         // add it
	         multipart.addBodyPart(messageBodyPart);
			
//			// 設定信中的內容
//			// message.setContent(messageText,"text/html;charset=utf-8");
//			message.setContent("<h1>Hi" + name + "</h1><br><br>" + "<img src='" + messageText + "'>", "text/html");
////			message.setText(messageText);
	         
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(
	            "D:\\2CA103_JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\T3MS\\img\\QRcode\\2018-10-11 09：38：13-265.jpg");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	public void setmail(String memno, String name, String email) {

		String to = email;

		String subject = "MS購票通知";

		String ch_name = name;

		String messageText = "Hello! " + ch_name;
		System.out.println(ch_name);
		BACKUP_MailService2 mailService = new BACKUP_MailService2();
		mailService.sendMail(to, subject, messageText,"sdf");
	}

	public static void main(String[] args) throws IOException {

		// ＠來源 (圖片tomcat.gif)
//		InputStream in = new FileInputStream(
//				"D:\\2CA103_JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\T3MS\\img\\QRcode\\2018-10-11 09：35：38-888.jpg");
//		byte[] buffer1 = new byte[in.available()];
//		in.read(buffer1);
//		in.close();
//
//		// Base64編碼
//		Base64.Encoder encoder = Base64.getEncoder();
//		String encodedText = encoder.encodeToString(buffer1);
//		System.out.println(encodedText);
//
//		// ＠來源 Base64解碼
//		Base64.Decoder decoder = Base64.getDecoder();
//		byte[] buffer2 = decoder.decode(encodedText);
//
//		// 終點 (圖片tomcat2.gif)
//		OutputStream out = new FileOutputStream(
//				"D:\\2CA103_JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\T3MS\\img\\QRcode\\2018-10-11 09：35：38-888.jpg");
//		out.write(buffer2);
//		out.close();

		BACKUP_MailService2 service = new BACKUP_MailService2();
		service.sendMail("mark22013333@gmail.com", "dd", "aa", "xxx");
	}
}
