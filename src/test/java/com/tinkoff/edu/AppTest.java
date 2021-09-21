package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.FileLoanCalcRepo;
import com.tinkoff.edu.app.service.LoanCalcService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.tinkoff.edu.app.exceptions.*;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static com.tinkoff.edu.app.enums.LoanType.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private LoanCalcController calcController;
    private LoanRequest request;
    private String fio = "Петрова Александра Сергеевна";
    private FileLoanCalcRepo fileRepository;
    private Path path = Path.of("testfile.txt");
    private Path pathООО = Path.of("testfileOOO.txt");

    @BeforeEach
    public void init() {
        //region Given
        fileRepository = new FileLoanCalcRepo(path);
        calcController = new LoanCalcController(new LoanCalcService(fileRepository));
        request = new LoanRequest(PERSON, 10, 1000, fio);
        //endregion

    }

    @Test
    @DisplayName("1: Проверка, что requestId не пустой")
    public void shouldGetNotNullWhenFirstResponse() {
        LoanResponse response = calcController.createRequest(request);
        assertNotEquals(null, response.getId());
    }

    @Test
    @DisplayName("2: Проверка, что requestId всегда разный")
    public void shouldGetDifferentIdAnyCall() {
        UUID firstCall = calcController.createRequest(request).getId();
        UUID secondCall = calcController.createRequest(request).getId();
        assertNotEquals(firstCall, secondCall);
    }

    @Test
    @DisplayName("3: Ошибка, если запрос null")
    public void shouldGetExceptionWhenApplyNullRequest() {
        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(path));
        assertThrows(IllegalArgumentException.class,
                () -> loanCalcService.createRequest(null));
    }

    @Test
    @DisplayName("4: Ошибка, если сумма = 0")
    public void shouldGetExceptionWhenApplyZeroAmountRequest() {
        request = new LoanRequest(PERSON, 10, 0, fio);
        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(path));
        assertThrows(IllegalArgumentException.class,
                () -> loanCalcService.createRequest(request));
    }

    @Test
    @DisplayName("5: Ошибка, если сумма отрицательная")
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {
        request = new LoanRequest(PERSON, 10, -10_000, fio);
        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(path));
        assertThrows(IllegalArgumentException.class,
                () -> loanCalcService.createRequest(request));
    }

    @Test
    @DisplayName("6: Ошибка, если количество месяцев = 0")
    public void shouldGetErrorWhenApplyZeroMonthsRequest() {
        request = new LoanRequest(OOO, 0, 12, fio);
        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(path));
        assertThrows(IllegalArgumentException.class,
                () -> loanCalcService.createRequest(request));
    }

    @Test
    @DisplayName("7: Ошибка, если количество месяцев отрицательное число")
    public void shouldGetErrorWhenApplyNegativeMonthsRequest() {
        request = new LoanRequest(OOO, -1, 12, fio);
        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(path));
        assertThrows(IllegalArgumentException.class,
                () -> loanCalcService.createRequest(request));
    }

    @Test
    @DisplayName("8: Approved for Person, amount=10_000, months=12")
    public void shouldGetApproveWhenPersonLess10000Less12Corner() {
        request = new LoanRequest(PERSON, 12, 10_000, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.APPROVED, response.getType());

    }

    @Test
    @DisplayName("9: Approved for Person, amount<10_000, months<12")
    public void shouldGetApproveWhenPersonLess10000Less() {
        request = new LoanRequest(PERSON, 11, 9_999, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.APPROVED, response.getType());
    }


    @Test
    @DisplayName("10: Declined for Person, amount>10_000, months>12")
    public void shouldGetDeclineWhenPersonMore10000More12() {
        request = new LoanRequest(PERSON, 13, 10_001, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName("11: Declined for OOO, amount<=10_000, any months")
    public void shouldGetDeclineWhenOOOLess10000() {
        request = new LoanRequest(OOO, 1, 10_000, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName("12: Approved for OOO, amount>10_000, months<12")
    public void shouldGetApproveWhenOOOMore10000Less12() {
        request = new LoanRequest(OOO, 11, 10_001, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.APPROVED, response.getType());
    }

    @Test
    @DisplayName("13: Declined for OOO, amount>10_000, months>=12")
    public void shouldGetDeclineWhenOOOMore10000More12() {
        request = new LoanRequest(OOO, 12, 10_001, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName("14: Declined for IP, any amount, any months")
    public void shouldGetDeclineWhenIP() {
        request = new LoanRequest(IP, 5, 5_000, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName("15: Approved for Person, amount>10_000, months<=12")
    public void shouldGetDeclinePersonMore10000Less12() {
        request = new LoanRequest(PERSON, 12, 10_001, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.APPROVED, response.getType());
    }

    @Test
    @DisplayName("16: Approved for Person, amount<10_000, months>=12")
    public void shouldGetDeclinePersonLess10000More12() {
        request = new LoanRequest(PERSON, 12, 9_999, fio);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(ResponseType.APPROVED, response.getType());
    }

    @Test
    @DisplayName("17: Получить статус по Id")
    public void shouldGetStatusById() {
        request = new LoanRequest(PERSON, 12, 9_999, fio);
        LoanResponse response = calcController.createRequest(request);
        UUID requestId = response.getId();
        assertEquals(response.getType(),
                calcController.getStatus(requestId));
    }

    @Test
    @DisplayName("18: Статус корректно обновляется по Id")
    public void shouldUpdateStatusById() {
        request = new LoanRequest(PERSON, 12, 9_999, fio);
        LoanResponse response = calcController.createRequest(request);
        UUID requestId = response.getId();
        assertEquals(ResponseType.APPROVED, calcController.updateStatus(requestId, ResponseType.APPROVED));
    }

    @Test
    @DisplayName("19: Ошибка при получении статуса по несуществующему Id")
    public void shouldGetExceptionWhenIdNotExist() {
        UUID requestId = UUID.randomUUID();
        assertThrows(RuntimeException.class,
                () -> calcController.getStatus(requestId));
    }

    @Test
    @DisplayName("20: Ошибка попытке обновить статус по несуществующему Id")
    public void shouldGetExceptionWhenUpdateStatusWhenBIdNotExist() {
        UUID requestId = UUID.randomUUID();
        assertThrows(RuntimeException.class,
                () -> calcController.updateStatus(requestId, ResponseType.APPROVED));
    }

    @Test
    @DisplayName("21: Эспешен, если fio длинное")
    public void shouldGetExceptionWhenFioLong() {
        request = new LoanRequest(PERSON, 12, 9_999, "Очень длинное фио более 100 символов Очень длинное фио более 100 символов Очень длинное фио более 100");
        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(path));
        assertThrows(FioLengthException.class,
                () -> loanCalcService.createRequest(request));
    }

    @Test
    @DisplayName("22: Эспешен,если fio короткое")
    public void shouldGetExceptionWhenFioShort() {
        request = new LoanRequest(PERSON, 12, 9_999, "Ф и о");
        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(path));
        assertThrows(FioLengthException.class,
                () -> loanCalcService.createRequest(request));
    }

    @Test
    @DisplayName("23. Поиск ООО ")
    public void shouldFindOOO() throws FioLengthException {
        fileRepository.clearFile();

        LoanCalcService loanCalcService = new LoanCalcService(new FileLoanCalcRepo(pathООО));

        LoanRequest requestООО = new LoanRequest(OOO, 11, 10_001, fio);
        loanCalcService.createRequest(requestООО);
        LoanRequest requestIP = new LoanRequest(IP, 11, 10_001, fio);
        loanCalcService.createRequest(requestIP);
        LoanRequest requestPerson = new LoanRequest(PERSON, 11, 10_001, fio);
        loanCalcService.createRequest(requestPerson);

        List<LoanResponse> responseList = loanCalcService.getApplicationsByLoanType(OOO);
        assertTrue(responseList.size() == 1);
        responseList.forEach(response -> assertEquals(response.getRequestType(), OOO));
    }

}
