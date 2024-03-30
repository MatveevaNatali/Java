package org.example;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IOService {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private Scanner sc;
    private OperationData operationData;
    private OperationService operationService;
    private CustomerService customerService;
    private StatementService statementService;
    private StorageService<OperationData> storageService;

    public IOService(Scanner sc) {
        this.sc = sc;
        storageService = new StorageService<>("./data.ser");
        loadData();
        operationService = new OperationService(operationData);
        customerService = new CustomerService(operationData);
        statementService = new StatementService(operationData);
    }

    public void start() {
        showMainMenu();
    }

    private void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            print("Выберите пункт меню:");
            print("1. Создать операцию");
            print("2. Создать пользователя");
            print("3. Показать всех пользователей");
            print("4. Показать операции пользователя");
            print("5. Сохранить данные");
            print("6. Выход");
            print(">>>");
            String menuItem = sc.nextLine();
            try {
                switch (menuItem) {
                    case "1":
                        createOperation();
                        break;
                    case "2":
                        createCustomer();
                        break;
                    case "3":
                        showCustomers();
                        break;
                    case "4":
                        showOperationsByCustomer();
                        break;
                    case "5":
                        saveData();
                        break;
                    default:
                        isRunning = false;
                }
            } catch (ParseException e) {
                print("Ошибка в введённой дате");
            }
        }
    }

    private void loadData() {
        try {
            operationData = storageService.deserialize();
            print("Данные успешно загружены");
        } catch (IOException e) {
            operationData = new OperationData();
            print("Не удаётся открыть файл для чтения или такого файла не существует");
        }
    }

    private void saveData() {
        try {
            storageService.serialize(operationData);
            print("Данные успешно сохранены");
        } catch (IOException e) {
            print("Не удаётся открыть файл для записи или такого файла не существует");
        }
    }

    private void createOperation() throws ParseException {
        String operationSelected = "";
        while (!List.of("1", "2", "3").contains(operationSelected)) {
            print("Выберите операцию:");
            print("1. Перевод");
            print("2. Кэшбек");
            print("3. Кредит");
            operationSelected = sc.nextLine();
        }

        printf("Создание операции номер: %d\n", operationService.getSize());
        print("Введите тип: ");
        String type = sc.nextLine();

        print("Введите сумму: ");
        int amount = Integer.parseInt(sc.nextLine());

        print("Введите дату: ");
        Date date = df.parse(sc.nextLine());

        int oid;
        if ("2".equals(operationSelected)) {
            print("Введите размер кэшбека: ");
            int cashbackAmount = Integer.parseInt(sc.nextLine());
            oid = operationService.addCashbackOperation(type, amount, cashbackAmount, date);
        } else if ("3".equals(operationSelected)) {
            print("Введите размер кредита: ");
            int loanId = Integer.parseInt(sc.nextLine());
            oid = operationService.addLoanOperation(type, amount, loanId, date);
        } else {
            oid = operationService.addOperation(type, amount, date);
        }

        addOperationToStatement(oid);

        print();
    }

    private void addOperationToStatement(int oid) throws ParseException {
        boolean enterNew = true;
        int cid = 0;
        while (enterNew) {
            print("Введите id пользователя: ");
            cid = Integer.parseInt(sc.nextLine());
            if (cid >= customerService.getSize() || cid < 0) {
                print("Такой пользователь не найден. Создать нового или ввести id заново?");
                print("1. Создать нового");
                print("2. Ввести заново");
                if ("1".equals(sc.nextLine())) {
                    cid = createCustomer();
                    enterNew = false;
                }
            }
        }
        statementService.addStatement(cid, oid);
    }

    private int createCustomer() throws ParseException {
        printf("Создание пользователя номер: %d\n", customerService.getSize());
        print("Введите полное имя: ");
        String name = sc.nextLine();
        print("Введите дату рождения: ");
        Date birthDt = df.parse(sc.nextLine());
        int cid = customerService.addCustomer(name, birthDt);
        statementService.addStatementForCustomer();
        print();
        return cid;
    }

    private void showCustomers() {
        for (Customer customer : customerService.getCustomers()) {
            print(customer.toString());
        }
        print();
    }

    private void showOperationsByCustomer() {
        print("Введите id пользователя: ");
        int cid = Integer.parseInt(sc.nextLine());

        if (cid >= customerService.getSize() || cid < 0) {
            print("Такой пользователь не найден");
            return;
        }

        for (Integer opId : statementService.getOperationByCustomer(cid)) {
            operationService.getOperations().get(opId).print();
        }
        print();
    }

    private void print() {
        System.out.println();
    }

    private void print(String str) {
        System.out.println(str);
    }

    private void printf(String str, Object... o) {
        System.out.printf(str, o);
    }
}
