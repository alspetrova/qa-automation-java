package com.tinkoff.edu.test;

import com.tinkoff.edu.app.model.*;
import com.tinkoff.edu.app.enums.*;
import com.tinkoff.edu.app.repository.*;
import com.tinkoff.edu.app.controller.*;
import com.tinkoff.edu.app.service.*;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String[] args) {
        LoanCalcController calcController = new LoanCalcController(new LoanCalcService(new DefaultLoanCalcRepository()));
        LoanRequest request = new LoanRequest(LoanType.PERSON, 10, 1000);
        LoanResponse response = calcController.createRequest(request);
        System.out.println(response);
    }
}
