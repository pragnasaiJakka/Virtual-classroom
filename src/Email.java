

import java.io.IOException;
import java.security.Security;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Email
 */
@WebServlet("/Email")
public class Email extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String to = request.getParameter("email");
		final String from = "ibm2014048@iiita.ac.in";
		final String pass = "Jeevan123";


		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String ssl_factory = "javax.net.ssl.SSLSocketFactory";

		Properties prop = new Properties();
		prop.setProperty("proxySet", "true");
		prop.setProperty("socksProxyHost","172.31.1.4");
		prop.setProperty("socksProxyPort","8080");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.setProperty("mail.smtp.socketFactory.class", ssl_factory);
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.port", "465");
		prop.setProperty("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.debug", "true");
		prop.put("mail.store.protocol", "pop3");
		prop.put("mail.transport.protocol", "smtp");


		Session session = Session.getInstance(prop, new javax.mail.Authenticator(){

			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(from,pass);
			}
		});

		try{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Email confirmation");
			message.setText("Hello");
			Transport.send(message);

			System.out.println("Message sent.");


		}catch(Exception e){
			e.printStackTrace();
		}


	}

}
