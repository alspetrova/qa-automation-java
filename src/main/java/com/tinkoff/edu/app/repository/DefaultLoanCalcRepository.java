package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.enums.*;

public class DefaultLoanCalcRepository implements LoanCalcRepository {
    private static int requestId;

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanRequest request) {
        return new LoanResponse(ResponseType.APPROVED, ++requestId, request);
    }

}
