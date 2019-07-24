package com.example.demo.dto;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageObjectConverter implements MessageConverter {

	private ObjectMapper objectMapper;
	
	public MessageObjectConverter() {
		objectMapper = new ObjectMapper();
	}
	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		MessageBody mo = (MessageBody)object;
		String payload = null;
		try {
			payload = objectMapper.writeValueAsString(mo);
			
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		TextMessage message = session.createTextMessage(payload);
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		TextMessage textMessage = (TextMessage)message;
		String payload = textMessage.getText();
		
		MessageBody mo = null;
		
		try {
			mo = objectMapper.readValue(payload, MessageBody.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mo;
	}

}
