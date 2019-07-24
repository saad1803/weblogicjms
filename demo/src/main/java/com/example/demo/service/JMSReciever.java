package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MessageBody;
import com.example.demo.util.Constants;

@Service
public class JMSReciever {

	private static final Logger log = LoggerFactory.getLogger(JMSReciever.class);
	
	@JmsListener(destination = Constants.JMS_QUEUE, containerFactory = "myFactory")
	public void recieveMessage(MessageBody mo) {
		log.info("Enterting recieveMessage");
		System.out.println(mo.toString());
	}
}
