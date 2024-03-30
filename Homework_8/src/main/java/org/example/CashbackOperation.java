package org.example;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    public CashbackOperation() {
        super();
    }

    public CashbackOperation(long id, String type, int amount, Date date, int cashbackAmount) {
        super(id, type, amount, date);
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void print() {
        System.out.printf("Операция: id:%d, тип:%s, сумма:%d, дата:%s, кэшбек:%d\n", id, type, amount, new SimpleDateFormat("yyyy-MM-dd").format(date), cashbackAmount);
    }
}
