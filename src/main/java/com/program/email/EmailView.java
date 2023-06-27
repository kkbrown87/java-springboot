package com.program.email;

import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailView {
  public void mailTest() throws Exception {
    String host = "pop.naver.com";
    String sendHost = "pop.naver.com";
    String username = "milkismania";
    String password = "unkunhee$Kbn1";
    String from = "kim871230@naver.com";

    // Create empty properties
    Properties props = System.getProperties();
    props.put("mail.smtp.host", sendHost);
    props.put("mail.smtp.port", 995);
    props.put("mail.smtp.ssl.protocols","TLSv1.2");
    props.put("mail.smtp.auth","true");

	Authenticator auth = new MyAuthentication();

    // Get session
    Session session = Session.getDefaultInstance(props, auth);
    session.setDebug(true);

    // Get the store
    Store store = session.getStore("pop3");
    store.connect(host, 995, username, password);

    // Get folder
    Folder folder = store.getFolder("INBOX");
    folder.open(Folder.READ_ONLY);

    BufferedReader reader = new BufferedReader (
      new InputStreamReader(System.in));

    // Get directory
    Message message[] = folder.getMessages();
    for (int i=0, n=message.length; i < n; i++) {
       System.out.println(i + ": " + message[i].getFrom()[0] + "t" + message[i].getSubject());

      System.out.println("Do you want to reply to the message? [YES to reply/QUIT to end]");
      String line = reader.readLine();
      if ("YES".equals(line)) {
    	// Create a reply message
          MimeMessage reply = (MimeMessage)message[i].reply(false);

          // Set the from field
          reply.setFrom(new InternetAddress(from));

          // Create the reply content, copying over the original if text
          MimeMessage orig = (MimeMessage)message[i];
          StringBuffer buffer = new StringBuffer("Thanksnn");
          if (orig.isMimeType("text/plain")) {
            String content = (String)orig.getContent();
            StringReader contentReader = new StringReader(content);
            BufferedReader br = new BufferedReader(contentReader);
            String contentLine;
            while ((contentLine = br.readLine()) != null) {
              buffer.append("> ");
              buffer.append(contentLine);
              buffer.append("rn");
            }
          }

          // Set the content
          reply.setText(buffer.toString());

          // Send the message
          Transport.send(reply);
      } else if ("QUIT".equals(line)) {
        break;
      }
    }

    // Close connection 
    folder.close(false);
    store.close();
  }
}

class MyAuthentication extends Authenticator {
	PasswordAuthentication pa;
	public MyAuthentication(){
		pa = new PasswordAuthentication("milkismania","unkunhee$Kbn1");
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}
