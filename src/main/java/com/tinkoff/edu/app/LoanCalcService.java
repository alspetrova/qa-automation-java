package com.tinkoff.edu.app;

public class LoanCalcService implements LoanCalcServiceInterface {
    private LoanCalcRepository repo; //Field DI

    /**
     * Constructor DI
     *
     * @param repo
     */
    public LoanCalcService(LoanCalcRepository repo) {
        this.repo = repo;
    }

    /**
     * TODO Loan calculation
     */
    @Override
    public LoanResponse createRequest(LoanRequest request) {
        int requestId = this.repo.save(request);
        return new LoanResponse(ResponseType.APPROVED, requestId, request);
    }
}
