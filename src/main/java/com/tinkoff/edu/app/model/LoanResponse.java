package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.*;

import java.util.UUID;

public class LoanResponse {

    private ResponseType type;
    private UUID requestId;
    private final LoanRequest request;

    public LoanResponse(ResponseType type, UUID requestId, LoanRequest request) {
        this.type = type;
        this.requestId = requestId;
        this.request = request;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public ResponseType getType() {
        return type;
    }

    public UUID getId() {
        return requestId;
    }

    public LoanRequest getRequest() {
        return request;
    }

    public String toString() {
        return "Response: {"
                + this.type + ", "
                + this.requestId + ", "
                + ", for "
                + this.getRequest()
                + "}";
    }
}
