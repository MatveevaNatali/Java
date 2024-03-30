package org.example.service;

import org.example.exception.CustomerOperationOutOfBoundException;
import org.example.model.Operation;
import org.example.model.OperationData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
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

    public void deleteStatementForCustomer(int customerId) {
        statement.remove(customerId);
    }

    public void addStatement(int customerId, Operation operation) {
        try {
            statement.get(customerId).add(operation);
        } catch (IndexOutOfBoundsException e) {
            throw new CustomerOperationOutOfBoundException(customerId, operation.getId());
        }
    }

    public void deleteStatement(int customerId, int operationId) {
        statement.get(customerId).removeIf(o -> o.getId() == operationId);
    }
}
