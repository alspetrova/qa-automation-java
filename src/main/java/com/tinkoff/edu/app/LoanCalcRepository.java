package com.tinkoff.edu.app;

public class LoanCalcRepository {
    private static int requestId;

    /**
     * TODO persist request
     * @return Request Id
     */
    public int save(LoanRequest request) {
        return ++requestId;
    }
}
