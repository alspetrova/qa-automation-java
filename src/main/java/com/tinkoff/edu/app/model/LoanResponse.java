package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.*;

import java.util.UUID;

public class LoanResponse {
    public void setType(ResponseType type) {
        this.type = type;
    }

    private ResponseType type;

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    private UUID requestId;
    private final LoanRequest request;

    public ResponseType getType() {
        return type;
    }

    public LoanResponse(ResponseType type, UUID requestId, LoanRequest request) {
        this.type = type;
        this.requestId = requestId;
        this.request = request;
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
