package com.example.demo.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.example.demo.util.Constants;

@Configuration
public class WebLogicExternalConnector {

	

	public Context initialContext() throws NamingException {
		return new InitialContext();
	}

	@Bean
	public ConnectionFactory connectionFactory() throws NamingException {
		return (ConnectionFactory) initialContext().lookup(Constants.JMS_MODULE);
	}

	@Bean
	public JmsListenerContainerFactory<?> myFactory(DefaultJmsListenerContainerFactoryConfigurer configurer)
			throws NamingException {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory());

		return factory;

	}

	@Bean
	public JmsTemplate jmsTemplate() throws NamingException {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setDefaultDestination(destination());
		jmsTemplate.setPubSubDomain(false);
		
		return jmsTemplate;
	}
	
	/*
	 * @Bean public MessageConverter jacksonJmsMessageConverter() {
	 * MappingJackson2MessageConverter converter = new
	 * MappingJackson2MessageConverter(); converter.setTargetType(MessageType.TEXT);
	 * converter.setTypeIdPropertyName("_type"); return converter; }
	 */

	
	 public Destination destination() throws NamingException {
		Destination d = (Destination)initialContext().lookup(Constants.JMS_QUEUE);
		
		return d;
	}
	/*
	 * @Bean public InitialContext weblogicInitialContextExt() {
	 * System.out.println("Initilizing Weblogic context");
	 * 
	 * Hashtable<String, String> h = new Hashtable<String, String>();
	 * h.put(Context.INITIAL_CONTEXT_FACTORY,
	 * "weblogic.jndi.WLInitialContextFactory"); h.put(Context.PROVIDER_URL,
	 * "t3://devmargo2-wl4:7001"); h.put(Context.SECURITY_PRINCIPAL, "weblogic");
	 * h.put(Context.SECURITY_CREDENTIALS, "webLogic!");
	 * 
	 * InitialContext ict = null;
	 * 
	 * try { ict = new InitialContext(h); } catch (NamingException e) {
	 * e.printStackTrace(); }
	 * 
	 * return ict;
	 * 
	 * }
	 * 
	 * private Properties getJNDIProperties() { Properties jndiProperties = new
	 * Properties(); jndiProperties.setProperty(Context.INITIAL_CONTEXT_FACTORY,
	 * "weblogic.jndi.WLInitialContextFactory");
	 * jndiProperties.setProperty(Context.PROVIDER_URL, "t3://devmargo2-wl4:7001");
	 * jndiProperties.setProperty(Context.SECURITY_PRINCIPAL, "weblogic");
	 * jndiProperties.setProperty(Context.SECURITY_CREDENTIALS, "webLogic!");
	 * 
	 * return jndiProperties; }
	 * 
	 * @Bean public JndiTemplate jndiTemplate() { JndiTemplate jndiTemplate = new
	 * JndiTemplate(); jndiTemplate.setEnvironment(getJNDIProperties());
	 * 
	 * return jndiTemplate; }
	 * 
	 * @Bean(name = "jmsJndiConnectionFactory") public JndiObjectFactoryBean
	 * jndiObjectFactoryBean() { JndiObjectFactoryBean jofb = new
	 * JndiObjectFactoryBean(); jofb.setJndiTemplate(jndiTemplate());
	 * jofb.setJndiName(Constants.JMS_MODULE);
	 * 
	 * return jofb; }
	 * 
	 * @Bean(name = "jmsWlsConnectionFactory") public ConnectionFactory
	 * jmsConnectionFactory(final JndiObjectFactoryBean jndiObjectFactoryBean) {
	 * ConnectionFactory connectionFactory = (ConnectionFactory)
	 * jndiObjectFactoryBean.getObject();
	 * 
	 * return connectionFactory; }
	 */

	/*
	 * @Bean(name = "jmsConnectionFactory")
	 * 
	 * @Primary public ConnectionFactory connectionFactoryProxy() {
	 * CachingConnectionFactory jmsConnectionFactory = new CachingConnectionFactory(
	 * (ConnectionFactory) appContext.getBean("jmsWlsConnectionFactory"));
	 * jmsConnectionFactory.setCacheProducers(true);
	 * jmsConnectionFactory.setSessionCacheSize(20); return jmsConnectionFactory; }
	 * 
	 * @Bean public JmsTemplate jmsTemplate() { JmsTemplate jms = new
	 * JmsTemplate((ConnectionFactory) appContext.getBean("jmsConnectionFactory"));
	 * 
	 * return jms; }
	 */

}
