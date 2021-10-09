package com.sap.queuepublisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class EtenderingMockController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/send")
    public void send(@RequestBody TedDocumentEntity transaction) {
        System.out.println("Sending a transaction.");
        // Post message to the message queue named "OrderTransactionQueue"
        jmsTemplate.convertAndSend("OrderTransactionQueue", transaction);
    }
}
