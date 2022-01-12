package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FileAccountRepository implements AccountRepository {

    @NonNull String filePath;

    public Map<Long, Set<Long>> readAccountsFromFile(){
        HashMap<Long, Set<Long>> clientIdToContractNumber = new HashMap<>();

        String NO_DIGIT_REGEX = "\\D"; // Для удаления всех нечисловых символов

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"clientId\":")) {
                    Long clientId = Long.valueOf(line.replaceAll(NO_DIGIT_REGEX, ""));

                    String nextLine = reader.readLine();
                    Long contractNumber = Long.valueOf(nextLine.replaceAll(NO_DIGIT_REGEX, ""));

                    if (clientIdToContractNumber.containsKey(clientId))
                        clientIdToContractNumber.get(clientId).add(contractNumber);
                    else
                        clientIdToContractNumber.put(clientId, new HashSet<>(Arrays.asList(contractNumber)));
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Файл не существует!");
        }
        return clientIdToContractNumber;

    }

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) {
        Map<Long, Set<Long>> clientIdToContractNumber = readAccountsFromFile();

        if (!clientIdToContractNumber.containsKey(clientId))
            throw new RuntimeException("Несуществующий clientId!");

        return clientIdToContractNumber.get(clientId);
    }

    public void updateContractNumberByClientId(long clientId, long olgContractNumber, long newContractNumber) {
        String DIGIT_REGEX = "\\d"; // Для удаления всех числовых символов

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            ArrayList<String> fileContent = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
                if (line.contains("\"clientId\": " + clientId)) {
                    String nextLine = reader.readLine();

                    if (nextLine.contains("\"number\": " + olgContractNumber)) {
                        nextLine = nextLine.replaceAll(DIGIT_REGEX, "") + newContractNumber;
                    }
                    fileContent.add(nextLine);

                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(String.join("\n", fileContent));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Файл не существует!");
        }
    }

    @Override
    public BigDecimal getAccountBalanceByContractNumber(long contractNumber) {
        return null;
    }
}
