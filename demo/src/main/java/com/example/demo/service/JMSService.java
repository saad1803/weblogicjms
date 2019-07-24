package com.example.demo.service;

import java.util.concurrent.atomic.AtomicReference;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MessageBody;
import com.example.demo.util.Constants;

@Service
public class JMSService {
	
	private static final Logger log = LoggerFactory.getLogger(JMSService.class);
 
	@Autowired
	public JmsTemplate jmsTemplate;

	public void sendMessage(MessageBody mo) throws JMSException {
		log.info("In Send Message");
		jmsTemplate.convertAndSend(mo);
		
	}
	
	
}
