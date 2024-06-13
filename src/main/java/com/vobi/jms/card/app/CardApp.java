package com.vobi.jms.card.app;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.vobi.jms.card.model.CreditCard;



public class CardApp {
	
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/creditCardTopic");

		String url = "tcp://localhost:61616";
		String user = "artemis";
		String password = "artemis";
				
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(url, user, password); JMSContext jmsContext = cf.createContext()){
			

			for (int i = 1; i <= 5; i++) {
				CreditCard creditCard =new CreditCard("1234 5678 9101 1121", "John Doe", "12/25", "123");
				jmsContext.createProducer().send(topic, creditCard);
				creditCard=null;
			}

			System.out.println("Message Sent");

		}

	}

}
