package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.model.*;

import java.util.UUID;
import java.util.Map;

public interface LoanCalcRepository {
    UUID save(LoanRequest request, ResponseType responseType);

    LoanResponse getItemById(UUID requestId);

    Map<UUID, LoanResponse> getApplications();

}
