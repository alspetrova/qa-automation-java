package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String[] args) {
        LoanCalcController calcController = new LoanCalcController();
        LoanRequest request = new LoanRequest(LoanType.PERSON, 10, 1000);
        LoanResponse response = calcController.createRequest(request);
        System.out.println(response);
    }
}
