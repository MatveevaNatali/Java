package org.example;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    public LoanOperation() {
        super();
    }

    public LoanOperation(long id, String type, int amount, Date date, int loanId) {
        super(id, type, amount, date);
        this.loanId = loanId;
    }

    @Override
    public void print() {
        System.out.printf("Операция: id:%d, тип:%s, сумма:%d, дата:%s, размер кредита:%d\n", id, type, amount, new SimpleDateFormat("yyyy-MM-dd").format(date), loanId);
    }
}
