package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

public class MapLoanCalcRepository implements LoanCalcRepository {

    private final Map<UUID, LoanResponse> mapRepository = new HashMap<>();

  /*  public static LoanCalcRow[] arrayRepository = new LoanCalcRow[100];
    private static int position;
    private UUID requestId;*/

    @Override
    public UUID save(LoanRequest request,ResponseType responseType) {
        UUID requestId = UUID.randomUUID();
        LoanResponse response = new LoanResponse(responseType,requestId,request);
        response.setRequestId(requestId);
        this.mapRepository.put(requestId,
                response);
        return requestId;
    }
    @Override
    public LoanResponse getItemById(UUID requestId) {
        return this.mapRepository.get(requestId);
    }

  @Override
    public Map<UUID, LoanResponse> getApplications() {
        return Collections.unmodifiableMap(mapRepository);
    }

}


