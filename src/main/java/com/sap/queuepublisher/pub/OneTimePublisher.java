package com.sap.queuepublisher.pub;

import java.util.List;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OneTimePublisher {

    public JmsConnectionFactory getConnectionFactory(String user, String pass, String url) {

        return new JmsConnectionFactory(user, pass, url);

    }

    public void publish(String user, String pass, String url, String queue, List<String> messages) throws JMSException {
        Connection connection;
        log.debug("====================sending");
        connection = getConnectionFactory(user, pass, url).createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queueDestination = session.createQueue(queue);
        MessageProducer produce = session.createProducer(queueDestination);
        connection.start();

        for (String msg : messages) {
            TextMessage message = session.createTextMessage(msg);
            produce.send(message);

        }
        connection.close();

    }

}
