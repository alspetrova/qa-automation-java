package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.DefaultLoanCalcRepository;
import com.tinkoff.edu.app.service.LoanCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private LoanCalcController calcController;
    private LoanRequest request;

    @BeforeEach
    public void init() {
        //region Given
        calcController = new LoanCalcController(new LoanCalcService(new DefaultLoanCalcRepository()));
        request = new LoanRequest(LoanType.PERSON, 10, 1000);
        //endregion
    }

    @Test
    @DisplayName("1: Проверка, что requestId равен 1 после первого создания запроса")
    public void shouldGetOneWhenFirstResponse() {
        //region When
        LoanResponse response = calcController.createRequest(request);
        //endregion
        //region Then
        assertEquals(1, response.getRequestId());
        //endregion
    }

    @Test
    @DisplayName("2: Проверка, что requestId увеличивается на 1 после каждого создания запроса")
    public void shouldGetIncrementedIdAnyCall() {
        int firstCall = calcController.createRequest(request).getRequestId();
        int secondCall = calcController.createRequest(request).getRequestId();
        assertEquals(firstCall + 1, secondCall);
    }

    @Test
    @DisplayName("3: Ошибка, если запрос null")
    public void shouldGetErrorWhenApplyNullRequest(){
        LoanResponse response = calcController.createRequest(null);
        assertEquals(-1, response.getRequestId());
   }

    @Test
    @DisplayName("4: Ошибка, если сумма = 0")
    public void shouldGetErrorWhenApplyZeroAmountRequest(){
        request = new LoanRequest(LoanType.PERSON, 10, 0);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(-1, response.getRequestId());
    }

    @Test
    @DisplayName("5: Ошибка, если сумма отрицательная")
    public void shouldGetErrorWhenApplyNegativeAmountRequest(){
        request = new LoanRequest(LoanType.PERSON, 10, -10_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(-1, response.getRequestId());
    }

    @Test
    @DisplayName("6: Ошибка, если количество месяцев = 0")
    public void shouldGetErrorWhenApplyZeroMonthsRequest(){
        request = new LoanRequest(LoanType.OOO, 0, 12);
       LoanResponse response = calcController.createRequest(request);
       assertEquals(-1, response.getRequestId());
    }

    @Test
    @DisplayName("7: Ошибка, если количество месяцев отрицательное число")
    public void shouldGetErrorWhenApplyNegativeMonthsRequest(){
        request = new LoanRequest(LoanType.OOO, -1, 12);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(-1, response.getRequestId());
    }

    @Test
    @DisplayName("8: Approved for Person, amount=10_000, months=12")
    public void shouldGetApproveWhenPersonLess10000Less12Corner(){
        request = new LoanRequest(LoanType.PERSON, 12, 10_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }

    @Test
    @DisplayName("9: Approved for Person, amount<10_000, months<12")
    public void shouldGetApproveWhenPersonLess10000Less(){
        request = new LoanRequest(LoanType.PERSON, 11, 9_999);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }


    @Test
    @DisplayName("10: Declined for Person, amount>10_000, months>12")
    public void shouldGetDeclineWhenPersonMore10000More12(){
        request = new LoanRequest(LoanType.PERSON, 13, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }

    @Test
    @DisplayName("11: Declined for OOO, amount<=10_000, any months")
    public void shouldGetDeclineWhenOOOLess10000(){
        request = new LoanRequest(LoanType.OOO, 1, 10_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }

    @Test
    @DisplayName("12: Approved for OOO, amount>10_000, months<12")
    public void shouldGetApproveWhenOOOMore10000Less12(){
        request = new LoanRequest(LoanType.OOO, 11, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }

    @Test
    @DisplayName("13: Declined for OOO, amount>10_000, months>=12")
    public void shouldGetDeclineWhenOOOMore10000More12(){
        request = new LoanRequest(LoanType.OOO, 12, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }

    @Test
    @DisplayName("14: Declined for IP, any amount, any months")
    public void shouldGetDeclineWhenIP(){
        request = new LoanRequest(LoanType.IP, 5, 5_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }

    @Test
    @DisplayName("15: Declined for Person, amount>10_000, months<=12")
    public void shouldGetDeclinePersonMore10000Less12(){
        request = new LoanRequest(LoanType.PERSON, 12, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }

    @Test
    @DisplayName("16: Declined for Person, amount<10_000, months>=12")
    public void shouldGetDeclinePersonLess10000More12(){
        request = new LoanRequest(LoanType.PERSON, 12, 9_999);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getRequestId());
    }



}
