// Домашнее задание №3 на 27.02.2024

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws Exception {
        // Переменные для итогового вывода
        StringBuilder sb = new StringBuilder();

        // Переменные для цикла
        Scanner scan = new Scanner(System.in);
        String nameOfTransaction;
        int sum;
        double percent;
        LocalDate date;

        while(true){
            System.out.println("Вы хотите ввести данные по транзации: да/нет");
            String input = scan.nextLine();

            // условие выхода
            if (input.equals("нет")){
                break;
            };

            //тело цикла
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

            //запись в строку одной транзакции
            String transresult =
                    "\n ------------------------------"+
                    "\n Транзакция: "  + nameOfTransaction +
                    "\n в сумме: " + sum +
                    "\n по ключевой ставке: " + percent +
                    "\n была совершена: " + date;

            sb.append(transresult);
        }

        //формирование итогового вывода по всем транзакциям
        String result = sb.toString();
        System.out.println(result);
    }
}