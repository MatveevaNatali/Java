package org.example.service;

import org.example.model.Operation;
import org.example.model.OperationData;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
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

    public void deleteOperation(int id) {
        operations.remove(id);
    }
}
