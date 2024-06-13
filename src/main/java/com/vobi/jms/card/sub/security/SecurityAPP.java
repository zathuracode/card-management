package com.vobi.jms.card.sub.security;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.vobi.jms.card.model.CreditCard;



public class SecurityAPP {
	
	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/creditCardTopic");

		String url = "tcp://localhost:61616";
		String user = "artemis";
		String password = "artemis";
				
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(url, user, password); JMSContext jmsContext = cf.createContext()){
			
			jmsContext.setClientID("securityAPP");			
			JMSConsumer consumer1 = jmsContext.createSharedConsumer(topic, "sharedConsumer");
			JMSConsumer consumer2 = jmsContext.createSharedConsumer(topic, "sharedConsumer");

			while(true) {
				Message message1 = consumer1.receive();
				CreditCard creditCard1 = message1.getBody(CreditCard.class);
				System.out.println(creditCard1.getCardNumber());
				System.out.println(creditCard1.getCardHolder());
				
				
				Message message2 = consumer2.receive();
				CreditCard creditCard2 = message2.getBody(CreditCard.class);
				System.out.println(creditCard2.getCardNumber());
				System.out.println(creditCard2.getCardHolder());
				
				creditCard1=null;
				creditCard2=null;
			}
		}

	}

}
