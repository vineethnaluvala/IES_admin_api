package in.vini.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	private Logger logger = LoggerFactory.getLogger(EmailUtils.class);

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendMail(String to, String subject, String body) {
		boolean isSent = false;
		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);

			mailSender.send(mimeMessage);

			isSent = true;

		} catch (MessagingException e) {

			logger.error(e.getMessage());

		}

		return isSent;

	}
}
