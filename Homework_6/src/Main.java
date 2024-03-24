public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //Создадим двух пользователей
        Customer customer1 = new Customer();
        Customer customer2 = new Customer(2,"Иван", 55);

        //Создадим две операции для customer1
        Operation operation11 = new Operation();
        Operation operation12 = new Operation(2,1,"заем", 300);

        //объекты классов-наследников
        CashbackOperation operation13 = new CashbackOperation(3,1,"кешбек",10,8);
        LoanOperation operation23 = new LoanOperation(6,2,"Займ", 67,8);

        //Создадим две операции для customer2
        Operation operation21 = new Operation(4,2,"депозит1", 500);
        Operation operation22 = new Operation(5,2,"депозит2", 1000);

        //проверим геттер
        System.out.println(customer1.getNameOfCust());
        System.out.println(customer2.getNameOfCust());

        System.out.println(operation12.getNameOfOperation());
        System.out.println(operation22.getNameOfOperation());

        //проверим сеттер
        customer1.setNameOfCust("Джон Уик");
        customer2.setNameOfCust("Антон");
        System.out.println(customer1.getNameOfCust());
        System.out.println(customer2.getNameOfCust());

        //Проверим работу метода print
        operation12.print();
        operation13.print();

        //Массив для хранения операций
        Operation[] operations = {operation11, operation21, operation22, operation21};
        //Массив для хранения клиентов
        Customer[] customers = {customer1,customer2};
        //
        int[][] statement = {};

    }
}