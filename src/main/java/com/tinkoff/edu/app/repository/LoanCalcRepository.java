package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.*;

public interface LoanCalcRepository {
    LoanResponse save(LoanRequest request);
}
