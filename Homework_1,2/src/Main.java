import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Домашнее задание на 21.02.2024
        String nameOfTransaction;
        int sum;
        double percent;
        LocalDate date;

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Введите название операции");
        nameOfTransaction = scanner1.nextLine(); //ожидание ввода

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Введите сумму транзакции");
        sum = scanner2.nextInt(); //ожидание ввода

        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Введите ставку");
        percent = scanner3.nextDouble(); //ожидание ввода

        Scanner scanner4 = new Scanner(System.in);
        System.out.println("Введите дату осуществления транзакции в формате: yyyy.MM.dd");
        String dateString = scanner4.nextLine();
        var f = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        date = LocalDate.parse(dateString, f);

        //Реализация вывода в одну строку
        System.out.println(
                "Транзакция: "  + nameOfTransaction +
                " в сумме: " + sum +
                " по ключевой ставке: " + percent +
                " была совершена: " + date);

        //Домашнее задание на 17.02.2024
        //System.out.println("Hello world!");
    }
}