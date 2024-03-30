package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatementService {
    private Map<Integer, List<Operation>> statement;

    public StatementService(OperationData operationData) {
        statement = operationData.statement;
    }

    public List<Operation> getOperationByCustomer(int customerId) {
        return statement.get(customerId);
    }

    public void addStatementForCustomer(int customerId) {
        statement.put(customerId, new ArrayList<>());
    }

    public void addStatement(int customerId, Operation operation) {
        try {
            statement.get(customerId).add(operation);
        } catch (IndexOutOfBoundsException e) {
            throw new CustomerOperationOutOfBoundException(customerId, operation.getId());
        }
    }
}
