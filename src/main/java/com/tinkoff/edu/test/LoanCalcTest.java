package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String[] args) {
        LoanCalcController calcController = new LoanCalcController();
        LoanRequest request = new LoanRequest(LoanType.PERSON, 10,1000);
        int requestId = calcController.createRequest(request);
        LoanResponse response = new LoanResponse(ResponseType.APPROVED,requestId,request);
        System.out.println(response);
    }
}
