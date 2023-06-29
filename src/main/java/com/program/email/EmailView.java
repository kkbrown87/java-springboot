package com.program.email;

import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailView {
  public void mailTest() throws Exception {
	String host = "pop.naver.com";
    String sendHost = "pop.naver.com";
    String username = "kim871230";
    String password = "unkunhee$Kbn0";
    String from = "kim871230@naver.com";

    // Create empty properties
    Properties props = System.getProperties();
    props.put("mail.smtp.host", sendHost);
    props.put("mail.smtp.port", "995");
    props.put("mail.smtp.ssl.protocols","TLSv1.2");
    props.put("mail.smtp.auth","true");

	Authenticator auth = new MyAuthentication();

    // Get session
    Session session = Session.getDefaultInstance(props, null);
    session.setDebug(true);

    // Get the store
    Store store = session.getStore("pop3");
    store.connect(host, 995, username, password);

    // Get folder
    Folder folder = store.getFolder("INBOX");
    folder.open(Folder.READ_ONLY);

    // Get directory
    Message message[] = folder.getMessages();
    
    for (Message msg : message) {
        // Process each message as needed
        System.out.println("Subject: " + msg.getSubject());
        System.out.println("From: " + msg.getFrom()[0]);
        System.out.println("Content: " + msg.getContent().toString());
    }

    // Close connection 
    folder.close(false);
    store.close();
  }
}

class MyAuthentication extends Authenticator {
	PasswordAuthentication pa;
	public MyAuthentication(){
		pa = new PasswordAuthentication("kim871230","unkunhee$Kbn0");
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}
