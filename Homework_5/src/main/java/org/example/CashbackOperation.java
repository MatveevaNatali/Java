package org.example;

import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    @Override
    public void print() {
        System.out.printf("Операция: id:%d, тип:%s, сумма:%d, дата:%s, кэшбек:%d\n", id, type, amount, new SimpleDateFormat("yyyy-MM-dd").format(date), cashbackAmount);
    }
}
