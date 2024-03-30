package org.example;

import java.util.Collection;
import java.util.Map;

public class OperationService {
    private Map<Integer, Operation> operations;
    private int lastId = 0;

    public OperationService(OperationData operationData) {
        operations = operationData.operations;
        lastId = operations.size();
    }

    public int getLastId() {
        return lastId++;
    }

    public Collection<Operation> getOperations() {
        return operations.values();
    }

    public void addOperation(Operation operation) {
        operations.put(operation.getId(), operation);
    }
}
