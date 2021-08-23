package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    private LoanCalcService loanCalcService;

    // public LoanCalcController(LoanCalcRepository repo) {
    //     this.loanCalcService = new IpNotFriendlyService(repo);
    // }
    public LoanCalcController(LoanCalcServiceInterface loanCalcService, LoanCalcRepository repo) {
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
