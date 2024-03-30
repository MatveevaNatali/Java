package org.example;

import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    @Override
    public void print() {
        System.out.printf("Операция: id:%d, тип:%s, сумма:%d, дата:%s, размер кредита:%d\n", id, type, amount, new SimpleDateFormat("yyyy-MM-dd").format(date), loanId);
    }
}
