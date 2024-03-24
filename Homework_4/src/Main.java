// Домашнее задание №4 на 28.02.2024

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws Exception {
        // Объявление неизменных переменных
        final int FIELD = 5;

        // Объявление одномерных массивов
        String[] nameOfTransactionMassive = new String[FIELD];
        int[] sumMassive = new int[FIELD];
        double[] percentMassive = new double[FIELD];
        LocalDate[] dateMassive = new LocalDate[FIELD];

        //Цикл для ввода 5 транзакций от 0 до 4
        for (int i =0; i<FIELD; i++){
            System.out.println("Введите данные для транзации №" + (i+1));
            //тело цикла
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Введите название операции");
            nameOfTransactionMassive[i] = scanner1.nextLine();

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Введите сумму транзакции");
            sumMassive[i] = scanner2.nextInt();

            Scanner scanner3 = new Scanner(System.in);
            System.out.println("Введите ставку");
            percentMassive[i] = scanner3.nextDouble();

            Scanner scanner4 = new Scanner(System.in);
            System.out.println("Введите дату осуществления транзакции в формате: yyyy.MM.dd");
            String dateString = scanner4.nextLine();
            var f = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            dateMassive[i] = LocalDate.parse(dateString, f);

            //Вывод одной транзации
            String transresult =
                    "\n ------------------------------"+
                            "\n Транзакция: "  + nameOfTransactionMassive[i] +
                            "\n в сумме: " + sumMassive[i] +
                            "\n по ключевой ставке: " + percentMassive[i] +
                            "\n была совершена: " + dateMassive[i];
            System.out.println(transresult);
            //конец цикла
        }

        System.out.println("Конец цикла");

        // Выведем наши данные по всем транзакциям
        System.out.println(Arrays.toString(nameOfTransactionMassive));
        System.out.println(Arrays.toString(sumMassive));
        System.out.println(Arrays.toString(percentMassive));
        System.out.println(Arrays.toString(dateMassive));

        // Реализация метода поиска транзакций по дате

        Scanner scannerBegin = new Scanner(System.in);
        System.out.println("Введите начало временного промежутка в формате: yyyy.MM.dd");
        String dateString2 = scannerBegin.nextLine();
        var f2 = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate dateBegin = LocalDate.parse(dateString2, f2);
        System.out.println(dateBegin);

        Scanner scannerEnd = new Scanner(System.in);
        System.out.println("Введите конец временного промежутка в формате: yyyy.MM.dd");
        String dateString3 = scannerEnd.nextLine();
        var f3 = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate dateEnd = LocalDate.parse(dateString3, f3);
        System.out.println(dateEnd);

        // теперь вызовем метод поиска по дате
        // передав в него полученные даты начала и конца

        findDate(dateBegin,dateEnd);
    }
    public static void findDate(LocalDate date1,LocalDate date2){
        System.out.println(date1);
        System.out.println(date2);
        // дописать поиск

    }
}