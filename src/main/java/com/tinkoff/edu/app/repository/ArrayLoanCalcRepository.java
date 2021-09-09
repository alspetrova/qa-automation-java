package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.exceptions.*;
import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.enums.*;

import java.util.UUID;

public class ArrayLoanCalcRepository implements LoanCalcRepository {

    public static LoanCalcRow[] arrayRepository = new LoanCalcRow[100];
    private static int position;
    private UUID requestId;

    @Override
    public UUID save(LoanRequest request, ResponseType response) {
        requestId = UUID.randomUUID();
        LoanCalcRow row = new LoanCalcRow(requestId,response);
        arrayRepository[position++] = row;
        return requestId;
    }

    public LoanCalcRow getRowById(UUID requestId){
        for (int i = 0; i < position; i++) {
            if ( arrayRepository[i].getRequestId().equals(requestId)) {
                return arrayRepository[i];
            }
        }
        throw new IllegalArgumentException("Нет заявки с таким Id");
    }
}
