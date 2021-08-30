package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.enums.*;

public class DefaultLoanCalcRepository implements LoanCalcRepository {
    private int requestId;

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanRequest request) {
        if  (request.getMonths() <=0 ||request.getAmount() <=0 || request==null)
            return new LoanResponse(ResponseType.DECLINED, -1, request);
        ResponseType responseType = calculateResponseType(request);
        return new LoanResponse(responseType, ++requestId, request);
    }

    public ResponseType calculateResponseType(LoanRequest request){
        if (request.getType().equals(LoanType.PERSON) & request.getMonths() <= 12 & request.getAmount() <= 10_000) {
            return ResponseType.APPROVED;
        }

        else if (request.getType().equals(LoanType.PERSON) & request.getMonths() > 12 & request.getAmount() > 10_000) {
            return ResponseType.DECLINED;
        }

        else if (request.getType().equals(LoanType.OOO) & request.getAmount() <= 10_000) {
            return ResponseType.DECLINED;
        }

        else if (request.getType().equals(LoanType.OOO) & request.getAmount() > 10_000 & request.getMonths() < 12) {
            return ResponseType.APPROVED;
        }

        else if (request.getType().equals(LoanType.OOO) & request.getAmount() > 10_000 & request.getMonths() >= 12) {
            return ResponseType.DECLINED;
        }

        else if (request.getType().equals(LoanType.IP)) {
            return ResponseType.DECLINED;
        }
        else return ResponseType.DECLINED;
    }

}
