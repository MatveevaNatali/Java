package org.example;

import java.util.Date;
import java.util.List;

public class OperationService {
    private List<Operation> operations;

    public OperationService(OperationData operationData) {
        operations = operationData.operations;
    }

    public int getSize() {
        return operations.size();
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public int addOperation(String type, int amount, Date date) {
        operations.add(new Operation(operations.size(), type, amount, date));
        return operations.size() - 1;
    }

    public int addCashbackOperation(String type, int amount, int cashbackAmount, Date date) {
        operations.add(new CashbackOperation(operations.size(), type, amount, date, cashbackAmount));
        return operations.size() - 1;
    }

    public int addLoanOperation(String type, int amount, int loanId, Date date) {
        operations.add(new LoanOperation(operations.size(), type, amount, date, loanId));
        return operations.size() - 1;
    }
}
