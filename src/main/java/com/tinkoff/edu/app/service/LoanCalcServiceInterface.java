package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.exceptions.FioLengthException;
import com.tinkoff.edu.app.model.*;

import java.util.UUID;

public interface LoanCalcServiceInterface {
    LoanResponse createRequest(LoanRequest request) throws FioLengthException;

    ResponseType getStatus(UUID uuid);

    ResponseType updateStatus(UUID uuid, ResponseType response);
}
