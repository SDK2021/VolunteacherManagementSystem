package com.volunteacher.app.config.configuration;

import java.util.Properties;
import java.util.Random;

import javax.naming.directory.NoSuchAttributeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class ConfigurationClass {
	
	@Autowired
	private Environment environment;
	//we can access all the propeerties from application.properties using Environment Object
	//or @Value annotation
	
	@Bean
	public JavaMailSender javamailSender()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(environment.getProperty("spring.mail.host"));
	    mailSender.setPort(Integer.valueOf(environment.getProperty("spring.mail.port")));
	    
	    mailSender.setUsername(environment.getProperty("spring.mail.username"));
	    mailSender.setPassword(environment.getProperty("spring.mail.password"));
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
}
