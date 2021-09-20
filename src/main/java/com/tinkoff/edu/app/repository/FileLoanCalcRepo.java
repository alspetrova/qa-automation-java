package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.tinkoff.edu.app.model.LoanResponse.fromString;
import static java.nio.file.StandardOpenOption.*;

public class FileLoanCalcRepo implements LoanCalcRepository {

    public Path path;

    public FileLoanCalcRepo(Path path) {
        this.path = path;
    }

    public UUID save(LoanRequest request, ResponseType responseType) {
        UUID requestId = UUID.randomUUID();
        LoanResponse response = new LoanResponse(request.getType(), request.getAmount(),
                request.getMonths(), request.getFio(), requestId, responseType);
        response.setRequestId(requestId);

        String loanRequestString = request.getType() + ", " +
                request.getAmount() + ", " + request.getMonths() + ", " +
                request.getFio() + ", " + response.getId() + ", " +
                response.getType();
        try {
            Files.writeString(path, loanRequestString + "\n", APPEND, CREATE, WRITE);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить строку в файл", e);
        }

        return requestId;

    }

    @Override
    public LoanResponse getItemById(UUID requestId) {
        var apps = getApplications();
        return apps.get(requestId);
    }

    @Override
    public Map<UUID, LoanResponse> getApplications() {

        var applicationsMap = new HashMap<UUID, LoanResponse>();
        try {
            var lines = Files.readAllLines(path);
            for (var l = 0; l < lines.size(); l++) {
                var application = fromString(lines.get(l));
                applicationsMap.put(application.getId(), application);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать строку из файла");
        }
        return applicationsMap;
    }

    public void clearFile() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("testfileOOO.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
