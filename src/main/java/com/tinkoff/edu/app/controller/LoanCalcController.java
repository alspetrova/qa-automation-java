package com.tinkoff.edu.app.controller;

import static com.tinkoff.edu.app.logger.LoanCalcLogger.log;

import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.exceptions.*;
import com.tinkoff.edu.app.service.*;
import com.tinkoff.edu.app.model.*;

import java.util.UUID;
import java.util.List;

public class LoanCalcController {
    private LoanCalcServiceInterface loanCalcService; //Field DI

    /**
     * Constructor DI
     *
     * @param loanCalcService
     */
    public LoanCalcController(LoanCalcServiceInterface loanCalcService) {
        this.loanCalcService = loanCalcService;
    }


    public LoanResponse createRequest(LoanRequest request) {
        //  log(request);*/
        LoanResponse loanResponse = null;
        try {
            loanResponse = loanCalcService.createRequest(request);
        } catch (FioLengthException ex) {
            System.out.println(ex.getStackTrace());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getStackTrace());
        }
        return loanResponse;
    }


    public ResponseType getStatus(UUID requestId) {
        return loanCalcService.getStatus(requestId);
    }


    public ResponseType updateStatus(UUID requestId, ResponseType response) {
        return loanCalcService.updateStatus(requestId, response);
    }

    public List<LoanResponse> getApplicationsByLoanType(LoanType requester) {
        return loanCalcService.getApplicationsByLoanType(requester);
    }
}
