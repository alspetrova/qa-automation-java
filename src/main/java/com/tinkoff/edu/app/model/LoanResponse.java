package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.*;

import java.util.UUID;

public class LoanResponse {

    private ResponseType responseType;
    private UUID requestId;
    private final LoanType requestType;
    private final int requestAmount;
    private final int requestMonths;
    private final String requestFio;


    public LoanResponse(LoanType requestType, int requestAmount, int requestMonths, String requestFio,
                        UUID requestId, ResponseType responseType) {
        this.requestType = requestType;
        this.requestAmount = requestAmount;
        this.requestMonths = requestMonths;
        this.requestFio = requestFio;
        this.requestId = requestId;
        this.responseType = responseType;
    }

    public void setType(ResponseType type) {
        this.responseType = type;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public ResponseType getType() {
        return responseType;
    }

    public UUID getId() {
        return requestId;
    }

    public static LoanResponse fromString(String str) {
        var arr = str.split(", ");

        var requestType = LoanType.valueOf(arr[0]);
        var amount = Integer.parseInt(arr[1]);
        var months = Integer.parseInt(arr[2]);
        var fio = arr[3];
        var requestId = UUID.fromString(arr[4]);
        var responseType = ResponseType.valueOf(arr[5]);


        return new LoanResponse(requestType, amount, months, fio, requestId, responseType);
    }

    public LoanType getRequestType() {
        return requestType;
    }
}
