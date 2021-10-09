package com.utilityServices.mqpublisher.publisher;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PublisherService {

	@Autowired
	private PublisherCredentials config;

	public JmsConnectionFactory getConnectionFactory() {

		return new JmsConnectionFactory(config.getUserName(), config.getPassword(), config.getBrokerUrl());

	}

//	@EventListener(ApplicationReadyEvent.class)
	private void run() throws JMSException {
		Connection connection;
		log.debug("====================sending");
		connection = getConnectionFactory().createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Queue queueDestination = session.createQueue("sap/cig/01/cXMLOutDeliveryQueue");
		Queue queueDestination2 = session.createQueue("sap/cig/01/CXMLDelivery_Queue");
		Queue queueDestination3 = session.createQueue("sap/cig/01/AS2Delivery_Queue");
		MessageProducer produce = session.createProducer(queueDestination);
		MessageProducer produce2 = session.createProducer(queueDestination2);
		MessageProducer produce3 = session.createProducer(queueDestination3);
		connection.start();

		for (int i = 0; i < 100; i++) {
			TextMessage message = session.createTextMessage("{\n" + "    \"productId\": \"CIG1\",\n"
					+ "    \"customerId\": \"AN010101011\",\n" + "    \"transactionId\": \"1234567754353513\",\n"
					+ "    \"serviceLogId\": \"835480086389279462\",\n" + "    \"documentId\": \"12232122121\",\n"
					+ "    \"documentType\": \"cXML\",\n" + "    \"destination\": \"http://AS2Delivery_Queue.url\",\n"
					+ "    \"status\": \"NEW\",\n" + "    \"stage\": \"NEW\",\n"
					+ "    \"queueName\": \"cXMLOutDeliveryQueue\"\n" + "}");
			TextMessage message2 = session.createTextMessage("{\n" + "    \"productId\": \"CIG1\",\n"
					+ "    \"customerId\": \"AN010101010\",\n" + "    \"transactionId\": \"" + Math.random() + "\",\n"
					+ "    \"serviceLogId\": \"835480086389279462\",\n" + "    \"documentId\": \"12232122121\",\n"
					+ "    \"documentType\": \"cXML\",\n" + "    \"destination\": \"http://AS2Delivery_Queue.url\",\n"
					+ "    \"status\": \"NEW\",\n" + "    \"stage\": \"NEW\",\n"
					+ "    \"queueName\": \"AS2Delivery_Queue\",\n"
					+ "    \"ExecutedTime\": \"2021-01-13T23:30:52.123Z\"\n" + "}");
			TextMessage message3 = session.createTextMessage("{\n" + "    \"productId\": \"CIG1\",\n"
					+ "    \"customerId\": \"AN010101010\",\n" + "    \"transactionId\": \"" + Math.random() + "\",\n"
					+ "    \"serviceLogId\": \"835480086389279462\",\n" + "    \"documentId\": \"12232122121\",\n"
					+ "    \"documentType\": \"cXML\",\n" + "    \"destination\": \"http://AS2Delivery_Queue.url\",\n"
					+ "    \"status\": \"NEW\",\n" + "    \"stage\": \"FAILED\",\n"
					+ "    \"queueName\": \"AS2Delivery_Queue\",\n"
					+ "    \"ExecutedTime\": \"2021-01-13T23:30:52.123Z\"\n" + "}");

			produce.send(message);
			produce2.send(message2);
			produce3.send(message3);

		}
		connection.close();

	}

}