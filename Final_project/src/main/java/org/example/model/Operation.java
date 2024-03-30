package org.example.model;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Operation implements Serializable {
    protected Integer id;         // Id транзакции
    protected String type;        // Тип транзакции
    protected int amount;         // Сумма
    protected Date date;          // Дата совершения

    public Operation() {
    }

    public Operation(Integer id, String type, int amount, Date date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public void print() {
        System.out.printf("Операция: id:%d, тип:%s, сумма:%d, дата:%s\n", id, type, amount, new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
}
