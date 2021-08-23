package com.tinkoff.edu.app.controller;

import static com.tinkoff.edu.app.logger.LoanCalcLogger.log;

import com.tinkoff.edu.app.service.*;
import com.tinkoff.edu.app.model.*;

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
