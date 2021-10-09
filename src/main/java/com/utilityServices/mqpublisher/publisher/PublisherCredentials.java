package com.utilityServices.mqpublisher.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@Data
public class PublisherCredentials {

    @Value("${vcap.services.${service-name.mq}.credentials.amqp10.url}")
    private String brokerUrl;

    @Value("${vcap.services.${service-name.mq}.credentials.amqp10.auth.basic.userName}")
    private String userName;

    @Value("${vcap.services.${service-name.mq}.credentials.amqp10.auth.basic.password}")
    private String password;

    @Value("${listener.queue.name}")
    private String queue;

}
