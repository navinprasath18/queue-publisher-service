package com.sap.queuepublisher.pub;

import lombok.Data;

@Data
public class PublisherData {

    private String queue;
    private String url;
    private String username;
    private String password;
    private String body;

}
