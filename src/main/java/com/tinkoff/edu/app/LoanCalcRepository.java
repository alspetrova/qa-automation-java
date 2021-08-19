package com.tinkoff.edu.app;

public class LoanCalcRepository {
    private static int requestId;

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    public LoanResponse save(LoanRequest request) {
        return new LoanResponse(ResponseType.APPROVED, ++requestId, request);
    }
}
