package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.repository.*;

public class IpNotFriendlyService extends LoanCalcService {
    public IpNotFriendlyService(LoanCalcRepository repo) {
        super(repo);
    }
}
