package com.vobi.jms.card.sub.alert;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.vobi.jms.card.model.CreditCard;



public class AlertAPP {
	
	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/creditCardTopic");

		String url = "tcp://localhost:61616";
		String user = "artemis";
		String password = "artemis";
				
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(url, user, password); JMSContext jmsContext = cf.createContext()){
			
			jmsContext.setClientID("alertAPP");
			JMSConsumer consumer = jmsContext.createDurableConsumer(topic, "subscriptionAlertAPP");
		
			
			while(true) {
				Message message = consumer.receive();
				CreditCard creditCard = message.getBody(CreditCard.class);
				System.out.println(creditCard.getCardNumber());
				System.out.println(creditCard.getCardHolder());
			}		
			
			//consumer.close();
			//jmsContext.unsubscribe("subscriptionAlertAPP");

		}

	}

}
