package com.sap.queuepublisher.controller;

import java.time.Instant;
import lombok.Data;

@Data
public class TedDocumentEntity {
    private String transactionId;

    private String documentId;

    private String responseId;

    private String documentType;

    private int counter;

    private boolean acknowledged;

    private Instant createdTime;

    private Instant verifiedTime;

    private String errorCode;

    private String errorMessage;

}
