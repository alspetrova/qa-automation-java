package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ResponseType;

import java.util.UUID;

public class LoanCalcRow {
    private final UUID requestId;
    private ResponseType status;

    public LoanCalcRow(UUID requestId, ResponseType status) {
        this.requestId = requestId;
        this.status = status;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public ResponseType getStatus() {
        return status;
    }

    public void setStatus(ResponseType status) {
        this.status = status;
    }
}
