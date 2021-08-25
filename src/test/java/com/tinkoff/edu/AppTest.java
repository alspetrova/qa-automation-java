package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.DefaultLoanCalcRepository;
import com.tinkoff.edu.app.repository.LoanCalcRepository;
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
    @DisplayName("Первый вызов")
    public void shouldGetOneWhenFirstResponse() {
        //region When
        LoanResponse response = calcController.createRequest(request);
        //endregion
        //region Then
        assertEquals(1, response.getRequestId());
        //endregion
    }

    @Test
    @DisplayName("Второй вызов")
    public void shouldGetIncrementedIdAnyCall() {
        int firstCall = calcController.createRequest(request).getRequestId();
        int secondCall = calcController.createRequest(request).getRequestId();
        assertEquals(firstCall + 1, secondCall);
    }
}
