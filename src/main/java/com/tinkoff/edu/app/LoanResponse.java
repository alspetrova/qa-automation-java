package com.tinkoff.edu.app;

public class LoanResponse {
    private final ResponseType type;
    private final int requestId;
    private final LoanRequest request;

    public LoanResponse(ResponseType type, int requestId, LoanRequest request) {
        this.type = type;
        this.requestId= requestId;
        this.request = request;
    }

    public int getRequestId() {
        return requestId;
    }

    public LoanRequest getRequest() {
        return request;
    }

    public String toString() {
        return "Response: {"
                + this.type + ","
                + this.getRequestId()
                + ", for " + this.getRequest() + "}";
    }
}
