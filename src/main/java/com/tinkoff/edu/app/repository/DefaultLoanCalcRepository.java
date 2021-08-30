package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.enums.*;

import static com.tinkoff.edu.app.enums.LoanType.*;
import static com.tinkoff.edu.app.enums.ResponseType.*;

public class DefaultLoanCalcRepository implements LoanCalcRepository {
    private int requestId;

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanRequest request) {
        if (request==null){
            return new LoanResponse(DECLINED, -1, request);
        }
        if  ((request.getMonths() <=0)||(request.getAmount() <=0)){
            return new LoanResponse(DECLINED, -1, request);
        }
        ResponseType responseType = calculateResponseType(request);
        return new LoanResponse(responseType, ++requestId, request);
    }

    public ResponseType calculateResponseType(LoanRequest request){
        if (request.getType().equals(PERSON)) {
            if (request.getAmount() <= 10000) {
                if (request.getMonths() <= 12) {
                    return APPROVED;
                } else {
                    //непокрытая ветка
                    return DECLINED;
                }
            } else {
                if (request.getMonths() > 12) {
                    return DECLINED;
                } else {
                    //непокрытая ветка
                    return DECLINED;
                }
            }
        }

        if (request.getType().equals(OOO)) {
            if (request.getAmount() <= 10000) {
                    return DECLINED;
            } else {
                if (request.getMonths() < 12) {
                    return APPROVED;
                } else {
                    return DECLINED;
                }
            }
        }

        if (request.getType().equals(IP)) {
            return DECLINED;
        }

        return DECLINED;
    }


}
