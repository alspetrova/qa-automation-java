package com.tinkoff.edu.app.controller;

import static com.tinkoff.edu.app.logger.LoanCalcLogger.log;

import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.exceptions.*;
import com.tinkoff.edu.app.service.*;
import com.tinkoff.edu.app.model.*;

import java.util.UUID;

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


    public LoanResponse createRequest(LoanRequest request) throws FioLengthException {
       //  log(request);*/
        try {
            return loanCalcService.createRequest(request);
        } catch (FioLengthException message) {
            throw new FioLengthException("Слишком короткое или слишком длинное ФИО");
        } catch (Exception message) {
            throw new IllegalArgumentException(message);
        }
    }


    public ResponseType getStatus(UUID requestId) {
         return loanCalcService.getStatus(requestId);
    }


    public ResponseType updateStatus(UUID requestId, ResponseType response) {
        return loanCalcService.updateStatus(requestId, response);
    }
}
