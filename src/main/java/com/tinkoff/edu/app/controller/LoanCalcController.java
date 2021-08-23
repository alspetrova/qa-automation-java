package com.tinkoff.edu.app.controller;

import static com.tinkoff.edu.app.logger.LoanCalcLogger.log;

import com.tinkoff.edu.app.repository.*;
import com.tinkoff.edu.app.service.*;
import com.tinkoff.edu.app.model.*;

public class LoanCalcController {
    private LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcServiceInterface loanCalcService) {
        LoanCalcRepository repo = new DefaultLoanCalcRepository();
        this.loanCalcService = new IpNotFriendlyService(repo);
    }

    /**
     * TODO Validates and logs request
     */
    public LoanResponse createRequest(LoanRequest request) {
        //param validation
        //log request
        log(request);
        return loanCalcService.createRequest(request);
    }
}
