package com.example.demo.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MessageBody;
import com.example.demo.dto.StatusObject;
import com.example.demo.service.JMSService;

@RestController
public class WeblogicController {

	private static final Logger log = LoggerFactory.getLogger(WeblogicController.class);
	
	@Autowired
	public JMSService jmsService;
	
	@PostMapping(path="/sendToJMSQueue", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public StatusObject sendToJMSQUeue(@RequestBody MessageBody messageDTO) {
		log.info("sendToJmsQueue");
		System.out.println("sendToJMSQueue");
		StatusObject so = new StatusObject();
		try {
		jmsService.sendMessage(messageDTO);
		
		so.setMessage("Message Sent to the queue");
		so.setStatus(true);
		so.setDate(new Date().toString());
		}catch(Exception e) {
			so.setMessage(e.getStackTrace().toString());
			so.setStatus(false);
			
			log.error(e.getMessage());
			e.printStackTrace();
			return so;
		}
		return so;
	}
	
	
}
