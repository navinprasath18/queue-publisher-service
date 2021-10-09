package com.sap.queuepublisher.pub;

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
public class PublishWithQueueName {
	@Autowired
	private PublisherCredentials config;

	Connection connection = null;

	public void setConnection() {

		try {
			connection = new JmsConnectionFactory(config.getUserName(), config.getPassword(), config.getBrokerUrl())
					.createConnection();
		} catch (JMSException e) {
			log.error("connection error");
		}

	}

	public String publish(String queuename, int msg) {
		try {
			if (connection == null) {
				setConnection();
			}
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Queue queueDestination = session.createQueue("sap/cig/01/" + queuename);
			MessageProducer produce = session.createProducer(queueDestination);
			connection.start();
			TextMessage message = session.createTextMessage("{\n" + "	\"ProductId\": \"CIG1\",\n"
					+ "	\"CustomerId\": \"AN02004814937-T\",\n"
					+ "	\"TransactionId\": \"4385f69c-5318-9fdd-a644-bceb141a243a\",\n"
					+ "	\"ServiceLogId\": \"AGBRZiyfgbSrocl-2M7kjCaehfI4\",\n"
					+ "	\"DocumentId\": \"cXML_Passthru_PO_Reprocess-01\",\n"
					+ "	\"DocumentType\": \"OrderRequest\",\n"
					+ "	\"Destination\": \"https://performance.cigrobot.c.eu-de-1.cloud.sap:8443/receiver/Inbound/receiveXML1\",\n"
					+ "	\"Status\": \"FAILED\",\n" + "	\"RetryCount\": null,\n"
					+ "	\"QueueName\": \"cXMLOutDeliveryQueue\",\n" + "	\"ExecutedTime\": \"2021-03-17 02:15:08.764\"\n"
					+ "}");
			for (int i = 0; i < msg; i++) {

				produce.send(message);
			}
			return "Success";
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
		return "Not success";
	}

	public String publish(String queuename, int msg, String body) {
		try {
			if (connection == null) {
				setConnection();
			}
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Queue queueDestination = session.createQueue("sap/cig/01/" + queuename);
			MessageProducer produce = session.createProducer(queueDestination);
			connection.start();
			TextMessage message = session.createTextMessage(body);
			for (int i = 0; i < msg; i++) {

				produce.send(message);
			}
			return "Success";
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
		return "Not success";
	}
}
