package org.example;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Operation implements ConsolePrintable, Serializable {
    protected long id;            // Id транзакции
    protected String type;        // Тип транзакции
    protected int amount;         // Сумма
    protected Date date;          // Дата совершения

    public Operation() {
    }

    public Operation(long id, String type, int amount, Date date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public void print() {
        System.out.printf("Операция: id:%d, тип:%s, сумма:%d, дата:%s\n", id, type, amount, new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
}
