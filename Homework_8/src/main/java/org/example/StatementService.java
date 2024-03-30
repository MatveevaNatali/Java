package org.example;

import java.util.ArrayList;
import java.util.List;

public class StatementService {
    private List<List<Integer>> statement;

    public StatementService(OperationData operationData) {
        statement = operationData.statement;
    }

    public List<List<Integer>> getStatement() {
        return statement;
    }

    public List<Integer> getOperationByCustomer(int customerId) {
        return statement.get(customerId);
    }

    public void addStatementForCustomer() {
        statement.add(new ArrayList<>());
    }

    public void addStatement(int customerId, int operationId) {
        try {
            statement.get(customerId).add(operationId);
        } catch (IndexOutOfBoundsException e) {
            throw new CustomerOperationOutOfBoundException(customerId, operationId);
        }
    }
}
