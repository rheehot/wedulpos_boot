package com.wedul.common.util;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * MailUtil
 * 
 * @author wedul
 *
 */
@Component
public class MailUtil {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	MessageBundleUtil messageBundleUtil;
	
	@Value("${email.mailgun.apiKey}")
    private String mailgunApiKey;

    @Value("${email.mailgun.host}")
    private String mailgunHost;
    
    @Value("${email.mailgun.from}")
    private String mailgunFrom;
	
	public MailUtil() {}
	
	/**
	 * Mail 전송
	 * 
	 * @param email
	 * @param otp
	 * @param subject
	 * @param content
	 * @throws Exception
	 */
	public void sendMailWithJava(String email, String subject, String content) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject(subject, "euc-kr");
		message.setText(content, "euc-kr", "html");
		message.addRecipient(RecipientType.TO, new InternetAddress(email));
		javaMailSender.send(message);
	}
	
	/**
     * Mail Send
     * 
     * @param toEmail
     * @param subject
     * @param content
     */
    public boolean sendMail(String toEmail, String subject, String content) {
    	Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", mailgunApiKey));
		WebResource webResource = client.resource(mailgunHost);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", mailgunFrom);
		formData.add("to", new StringBuffer(toEmail.split("@")[0]).append(" <").append(toEmail).append(">").toString());
	    formData.add("subject", subject);
	    formData.add("text", content);
	    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
    	                                        post(ClientResponse.class, formData).getClientResponseStatus().getStatusCode() == 200;
    }

}
