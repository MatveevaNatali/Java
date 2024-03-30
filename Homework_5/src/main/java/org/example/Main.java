package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    private static final int MAX_SIZE = 10;
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private static Operation[] operations = new Operation[MAX_SIZE];
    private static Customer[] customers = new Customer[MAX_SIZE];
    private static int[][] statement = new int[MAX_SIZE][];
    private static int operationsSize = 0;
    private static int customerSize = 0;

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Выберите пункт меню:");
            System.out.println("1. Создать операцию");
            System.out.println("2. Создать пользователя");
            System.out.println("3. Показать операции пользователя");
            System.out.println("4. Выход");
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
                    showOperationsByCustomer(sc);
                    break;
                default:
                    isRunning = false;
            }
        }

        for (int i=0; i<operationsSize; i++) {
            operations[i].print();
        }

        for(int i=0; i<customerSize; i++) {
            System.out.println(customers[i].toString());
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
        System.out.printf("Создание операции номер: %d\n", operationsSize);
        operation.setId(operationsSize);

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

        boolean enterNew = true;
        int cid = 0;
        while (enterNew) {
            System.out.println("Введите id пользователя: ");
            cid = Integer.parseInt(sc.nextLine());
            if (cid >= customerSize || cid < 0) {
                System.out.println("Такой пользователь не найден. Создать нового или ввести id заново?");
                System.out.println("1. Создать нового");
                System.out.println("2. Ввести заново");
                if ("1".equals(sc.nextLine())) {
                    cid = customerSize;
                    createCustomer(sc);
                    enterNew = false;
                }
            }
        }
        addOperationToStatement(cid);
        System.out.println();
        operations[operationsSize] = operation;
        operationsSize++;
    }

    private static void addOperationToStatement(int cid) {
        int lastEl = statement[cid].length;
        int[] newArr = new int[lastEl+1];
        for (int i=0; i<lastEl; i++) {
            newArr[i] = statement[cid][i];
        }
        newArr[lastEl] = operationsSize;
        statement[cid] = newArr;
    }

    private static void createCustomer(Scanner sc) throws ParseException {
        System.out.printf("Создание пользователя номер: %d\n", customerSize);
        Customer customer = new Customer();
        customer.setId(customerSize);
        System.out.println("Введите полное имя: ");
        customer.setName(sc.nextLine());
        System.out.println("Введите дату рождения: ");
        String dateStr = sc.nextLine();
        customer.setBirthDt(df.parse(dateStr));
        System.out.println();
        customers[customerSize] = customer;
        statement[customerSize] = new int[]{};
        customerSize++;
    }

    private static void showOperations() {
        for (int i=0; i<operationsSize; i++) {
            operations[i].print();
        }
    }

    private static void showCustomers() {
        for (int i=0; i<customerSize; i++) {
            System.out.println(customers[i].toString());
        }
    }

    private static void showOperationsByCustomer(Scanner sc) {
        System.out.println("Введите id пользователя: ");
        int cid = Integer.parseInt(sc.nextLine());

        if (cid >= customerSize || cid < 0) {
            System.out.println("Такой пользователь не найден");
            return;
        }

        for (int i=0; i<statement[cid].length; i++) {
            int oid = statement[cid][i];
            ((ConsolePrintable) operations[oid]).print();
        }
    }
}