package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    /**
     * TODO Validates and logs request
     */
    public static int createRequest(LoanRequest request) {
        //param validation
        //log request
        log(request);
        return LoanCalcService.createRequest(request);
    }
}
