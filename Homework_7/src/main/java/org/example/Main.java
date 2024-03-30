package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static OperationData od = new OperationData();

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        try {
            deserializeOperationData();
        } catch (IOException e) {
            System.out.println("Не удаётся открыть файл для чтения или такого файла не существует");
        }

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Выберите пункт меню:");
            System.out.println("1. Создать операцию");
            System.out.println("2. Создать пользователя");
            System.out.println("3. Показать всех пользователей");
            System.out.println("4. Показать операции пользователя");
            System.out.println("5. Сохранить данные");
            System.out.println("6. Выход");
            System.out.println(">>>");
            String menuItem = sc.nextLine();
            switch (menuItem) {
                case "1":
                    createOperation(sc);
                    break;
                case "2":
                    createCustomer(sc);
                    break;
                case "3":
                    showCustomers();
                    break;
                case "4":
                    showOperationsByCustomer(sc);
                    break;
                case "5":
                    try {
                        serializeOperationData();
                        System.out.println("Данные успешно сохранены");
                    } catch (IOException e) {
                        System.out.println("Не удаётся открыть файл для записи или такого файла не существует");
                    }
                    break;
                default:
                    isRunning = false;
            }
        }

        for (int i=0; i<od.operationsSize; i++) {
            od.operations[i].print();
        }

        for(int i=0; i<od.customerSize; i++) {
            System.out.println(od.customers[i].toString());
        }
    }

    public static void serializeOperationData() throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("./data.ser"))) {
            os.writeObject(od);
        }
    }

    public static void deserializeOperationData() throws IOException {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./data.ser"))) {
            od = (OperationData) is.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createOperation(Scanner sc) throws ParseException {
        Operation operation = null;
        boolean operationSelected = false;
        while (!operationSelected) {
            operationSelected = true;
            System.out.println("Выберите операцию:");
            System.out.println("1. Перевод");
            System.out.println("2. Кэшбек");
            System.out.println("3. Кредит");
            switch (sc.nextLine()) {
                case "1":
                    operation = new Operation();
                    break;
                case "2":
                    operation = new CashbackOperation();
                    break;
                case "3":
                    operation = new LoanOperation();
                    break;
                default:
                    operationSelected = false;
            }
        }
        System.out.printf("Создание операции номер: %d\n", od.operationsSize);
        operation.setId(od.operationsSize);

        System.out.println("Введите тип: ");
        operation.setType(sc.nextLine());

        System.out.println("Введите сумму: ");
        operation.setAmount(Integer.parseInt(sc.nextLine()));

        if (operation instanceof CashbackOperation) {
            System.out.println("Введите размер кэшбека: ");
            ((CashbackOperation) operation).setCashbackAmount(Integer.parseInt(sc.nextLine()));
        } else if (operation instanceof LoanOperation) {
            System.out.println("Введите размер кредита: ");
            ((LoanOperation) operation).setLoanId(Integer.parseInt(sc.nextLine()));
        }

        System.out.println("Введите дату: ");
        String dateStr = sc.nextLine();
        operation.setDate(df.parse(dateStr));

        addOperationToStatement(sc);

        System.out.println();
        od.operations[od.operationsSize] = operation;
        od.operationsSize++;
    }

    private static void addOperationToStatement(Scanner sc) throws ParseException {
        boolean enterNew = true;
        int cid = 0;
        while (enterNew) {
            System.out.println("Введите id пользователя: ");
            cid = Integer.parseInt(sc.nextLine());
            if (cid >= od.customerSize || cid < 0) {
                System.out.println("Такой пользователь не найден. Создать нового или ввести id заново?");
                System.out.println("1. Создать нового");
                System.out.println("2. Ввести заново");
                if ("1".equals(sc.nextLine())) {
                    cid = od.customerSize;
                    createCustomer(sc);
                    enterNew = false;
                }
            }
        }
        int lastEl = od.statement[cid].length;
        if (lastEl + 1 > od.MAX_SIZE) {
            throw new CustomerOperationOutOfBoundException(cid, od.operationsSize);
        }
        int[] newArr = new int[lastEl+1];
        for (int i=0; i<lastEl; i++) {
            newArr[i] = od.statement[cid][i];
        }
        newArr[lastEl] = od.operationsSize;
        od.statement[cid] = newArr;
    }

    private static void createCustomer(Scanner sc) throws ParseException {
        System.out.printf("Создание пользователя номер: %d\n", od.customerSize);
        Customer customer = new Customer();
        customer.setId(od.customerSize);
        System.out.println("Введите полное имя: ");
        customer.setName(sc.nextLine());
        System.out.println("Введите дату рождения: ");
        String dateStr = sc.nextLine();
        customer.setBirthDt(df.parse(dateStr));
        System.out.println();
        od.customers[od.customerSize] = customer;
        od.statement[od.customerSize] = new int[]{};
        od.customerSize++;
    }

    private static void showOperations() {
        for (int i=0; i<od.operationsSize; i++) {
            od.operations[i].print();
        }
    }

    private static void showCustomers() {
        for (int i=0; i<od.customerSize; i++) {
            System.out.println(od.customers[i].toString());
        }
    }

    private static void showOperationsByCustomer(Scanner sc) {
        System.out.println("Введите id пользователя: ");
        int cid = Integer.parseInt(sc.nextLine());

        if (cid >= od.customerSize || cid < 0) {
            System.out.println("Такой пользователь не найден");
            return;
        }

        for (int i=0; i<od.statement[cid].length; i++) {
            int oid = od.statement[cid][i];
            ((ConsolePrintable) od.operations[oid]).print();
        }
    }
}