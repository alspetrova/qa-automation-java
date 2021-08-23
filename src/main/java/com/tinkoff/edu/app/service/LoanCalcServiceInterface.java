package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.model.*;

public interface LoanCalcServiceInterface {
    LoanResponse createRequest(LoanRequest request);
}
