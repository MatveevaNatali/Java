public class Operation {
    // объявим поля
    protected int idOfOperation;
    protected int idOfCust;
    protected String nameOfOperation;
    protected int sum;

    //Конструктор без аргументов для заполнения дефолтными значениями
    public Operation(){
        this.idOfOperation = 1;
        this.idOfCust = 1;
        this.nameOfOperation = "кредит";
        this.sum = 100000;
    }
    // Конструктор с аргументами
    public Operation(int idOfOperation, int idOfCust, String nameOfOperation, int sum){
        this.idOfOperation = idOfOperation;
        this.idOfCust = idOfCust;
        this.nameOfOperation = nameOfOperation;
        this.sum = sum;
    }

    //Геттер
    public String getNameOfOperation() {
        return nameOfOperation;
    }

    //Сеттер
    public void setNameOfOperation(String name) {
        this.nameOfOperation = name;
    }

    //Метод вывода операции в консоль
    public void print(){
        System.out.println(idOfOperation);
        System.out.println(idOfCust);
        System.out.println(nameOfOperation);
        System.out.println(sum);
    }

}
