package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.*;
import com.tinkoff.edu.app.exceptions.FioLengthException;
import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.repository.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tinkoff.edu.app.enums.ResponseType.APPROVED;
import static com.tinkoff.edu.app.enums.ResponseType.DECLINED;

public class LoanCalcService implements LoanCalcServiceInterface {
    private LoanCalcRepository repo; //Field DI

    /**
     * Constructor DI
     *
     * @param repo
     */
    public LoanCalcService(LoanCalcRepository repo) {
        this.repo = repo;
    }

    @Override
    public LoanResponse createRequest(LoanRequest request) throws FioLengthException {

        if ((request == null) || (request.getMonths() <= 0) || (request.getAmount() <= 0)) {
            throw new IllegalArgumentException("Некорректные значения в request || months || amount");
        }

        if (request.getFio().length() < 10 || request.getFio().length() > 100) {
            throw new FioLengthException("Слишком короткое или слишком длинное имя");
        }

        ResponseType responseType = this.calculateResponseType(request);
        UUID requestId = this.repo.save(request,responseType);
        return new LoanResponse(request.getType(),request.getAmount(), request.getMonths(),
                request.getFio(),requestId, responseType);
    }

    public ResponseType calculateResponseType(LoanRequest request) {
        switch (request.getType()) {
            case PERSON: {
                if (request.getAmount() <= 10000) {
                    if (request.getMonths() <= 12) {
                        return APPROVED;
                    } else {
                        return DECLINED;
                    }
                } else {
                    if (request.getMonths() > 12) {
                        return DECLINED;
                    } else {
                        return APPROVED;
                    }
                }
            }

            case OOO: {
                if (request.getAmount() <= 10000) {
                    return DECLINED;
                } else {
                    if (request.getMonths() < 12) {
                        return APPROVED;
                    } else {
                        return DECLINED;
                    }
                }
            }

            case IP:
                return DECLINED;
        }
        return DECLINED;
    }

    @Override
    public ResponseType getStatus(UUID requestId) {
        try {
            return repo.getItemById(requestId).getType();
        } catch (NullPointerException e) {
            throw new RuntimeException("No application for this ID", e);
        }
    }

    @Override
    public ResponseType updateStatus(UUID requestId, ResponseType response_param) {
        LoanResponse response = repo.getItemById(requestId);
        try {
            response.setType(response_param);
        } catch (NullPointerException e) {
            throw new RuntimeException("No application for this ID", e);
        }
        return response.getType();
    }

    @Override
    public List<LoanResponse> getApplicationsByLoanType(LoanType requester) {
        Map<UUID, LoanResponse> applications = repo.getApplications();
        return applications.values().stream()
                .filter((application) -> application.getRequestType().equals(requester))
                .collect(Collectors.toList());
    }
}
