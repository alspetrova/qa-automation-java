package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.model.*;

import java.util.UUID;

public interface LoanCalcRepository {
    UUID save(LoanRequest request, ResponseType response);
    LoanCalcRow getRowById(UUID requestId);
}
