package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.repository.*;

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
        return repo.save(request);
    }
}
