package com.sap.queuepublisher.pub;

import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publish")
public class PublisherController {

    @Autowired
    private OneTimePublisher otp;

    @PostMapping()
    public String postMessage(@RequestBody PublisherData body) throws JMSException {
        List<String> list = new ArrayList<String>();
        String msg1 = "{}";
        list.add(msg1);
        otp.publish(body.getUsername(), body.getPassword(), body.getUrl(), body.getQueue(), list);
        return "Success";

    }

}
