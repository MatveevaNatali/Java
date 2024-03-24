public class Customer {
    // объявим поля
    private int id;
    // new comment
    private String nameOfCust;
    private int age;

    //Конструктор без аргументов для заполнения дефолтными значениями
    public Customer(){
        this.id = 1;
        this.nameOfCust = "Джон";
        this.age = 40;
    }
    // Конструктор с аргументами
    public Customer(int id, String nameOfCust, int age){
        this.id = id;
        this.nameOfCust = nameOfCust;
        this.age = age;
    }

    //Геттер
    public String getNameOfCust() {
        return nameOfCust;
    }

    //Сеттер
    public void setNameOfCust(String name) {
        this.nameOfCust = name;
    }
}
