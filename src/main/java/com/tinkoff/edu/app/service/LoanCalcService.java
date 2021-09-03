package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.*;
import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.repository.*;

import java.util.UUID;

import static com.tinkoff.edu.app.enums.ResponseType.APPROVED;
import static com.tinkoff.edu.app.enums.ResponseType.DECLINED;

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

    @Override
    public LoanResponse createRequest(LoanRequest request) {
        ResponseType responseType = this.calculateResponseType(request);
        UUID requestId = this.repo.save(request,responseType);
        return new LoanResponse(responseType,requestId,request);

    }

    public ResponseType calculateResponseType(LoanRequest request){
        switch (request.getType()) {
            case PERSON:{
                if (request.getAmount() <= 10000) {
                    if (request.getMonths() <= 12) {
                        return APPROVED;
                    } else {
                        return DECLINED;
                    }
                } else {
                    if (request.getMonths() > 12) {
                        return DECLINED;
                    } else {
                        return DECLINED;
                    }
                }
            }

            case OOO: {
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

            case IP:
                return DECLINED;
        }
        return DECLINED;
    }

    @Override
    public ResponseType getStatus(UUID requestId) {
        LoanCalcRow row = repo.getRowById(requestId);
        if (row==null)
                return ResponseType.ERROR;
        return row.getStatus();
    }

    @Override
    public ResponseType updateStatus(UUID requestId, ResponseType response) {
        LoanCalcRow row = repo.getRowById(requestId);
        if (row==null)
            return ResponseType.ERROR;
        row.setStatus(response);
        return row.getStatus();
    }
}
