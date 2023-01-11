package com.jacaranda.springProjecToWork.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacaranda.springProjecToWork.model.User;
import com.jacaranda.springProjecToWork.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public boolean checkExist(User s) {
	boolean resultado = false;
	// Comprueba que no existe el nombre del usuario
	User checkUser = repository.findById(s.getUser()).orElse(null);
	// Comprueba que no existe el email en la base de datos
	List<User> checkPassword =
	repository.findByEmail(s.getEmail());
	if (checkUser == null && checkPassword.size() == 0) {
	resultado = true;
	}
	return resultado;
	}
	
	private void sendVerificationEmail(User user, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
			String toAddress = user.getEmail();
			
			String fromAddress = "ioliasa@gmail.com";
			
			String senderName = "Jacaranda";
			
			String subject = "Please verify your registration";
			
			String content = "Dear [[user]],<br>" + "Please click the link below to verify your registration:<br>"
			+ "<h3><a href=\"[[URL]]\"target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "Your company name.";
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setFrom(fromAddress, senderName);
			helper.setTo(toAddress);
			helper.setSubject(subject);
			content = content.replace("[[user]]", user.getUser());
			String verifyURL = siteURL + "/verify?code=" +
			user.getVerificationCode();
			content = content.replace("[[URL]]", verifyURL);
			helper.setText(content, true);
			mailSender.send(message);
		}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws
		UsernameNotFoundException {
		User user = repository.findById(username).orElse(null);
		if (user == null) {
		throw new UsernameNotFoundException("User not found");
		}
		return user;
	}
	

	
	public void add(User s, String siteURL) throws
		UnsupportedEncodingException, MessagingException {
		BCryptPasswordEncoder passwordEncoder = new
		BCryptPasswordEncoder();
		String encodedPassword =
		passwordEncoder.encode(s.getPassword());
		s.setPassword(encodedPassword);
		// Genera una cadena aleatoria que guarderemos en el código
		// de verificación y que le enviaremos al usuario para saber
		// que es él.
		String randomCode = RandomString.make(64);
		s.setVerificationCode(randomCode);
		s.setEnabled(false);
		s.setRole("USER");
		repository.save(s);
		sendVerificationEmail(s, siteURL);
	}

	
	
	
}
