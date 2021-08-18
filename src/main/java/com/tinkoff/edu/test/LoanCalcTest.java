package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String[] args) {
        LoanCalcController calcController = new LoanCalcController();
        LoanRequest request = new LoanRequest(LoanType.PERSON, 10,1000);
        int requestId = calcController.createRequest(request);
        System.out.println(request);
    }
}
