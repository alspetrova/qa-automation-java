package com.tinkoff.edu.app;

public class StaticVariableLoanCalcRepository implements LoanCalcRepository {
    private static int requestId;

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public int save(LoanRequest request) {
        return ++requestId;
    }


}
