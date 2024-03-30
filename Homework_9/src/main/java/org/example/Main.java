package org.example;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        OperationData operationData;
        StorageService<OperationData> storageService = new StorageService<>("./data.ser");
        try {
            operationData = storageService.deserialize();
            System.out.println("Данные успешно загружены");
        } catch (IOException e) {
            operationData = new OperationData();
            System.out.println("Не удаётся открыть файл для чтения или такого файла не существует");
        }
        OperationService operationService = new OperationService(operationData);
        CustomerService customerService = new CustomerService(operationData);
        StatementService statementService = new StatementService(operationData);
        AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(operationService);
        asyncInputOperationService.startAsyncOperationProcessing();
        IOService ioService = new IOService(sc, operationData, storageService, asyncInputOperationService, customerService, statementService);
        ioService.start();
    }
}