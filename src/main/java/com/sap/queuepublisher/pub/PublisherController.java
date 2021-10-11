package com.sap.queuepublisher.pub;

import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import org.json.JSONArray;
import org.json.JSONObject;
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
    public String postMessage(@RequestBody String body) throws JMSException {
        List<String> list = new ArrayList<String>();
        var json = new JSONObject(body);
        JSONArray messageArray = json.getJSONArray("body");
        for (Object obj : messageArray) {
            list.add(obj.toString());
        }
        otp.publish(json.get("username").toString(),
                json.get("password").toString(), json.get("url").toString(),
                json.get("queue").toString(), list);
        return "Success";

    }

}
