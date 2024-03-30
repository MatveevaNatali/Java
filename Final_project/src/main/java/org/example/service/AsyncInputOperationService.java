package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.OperationProperties;
import org.example.model.Operation;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class AsyncInputOperationService {
    private Queue<Operation> queue = new LinkedList<>();
    private OperationService operationService;
    private OperationProperties operationProperties;

    @PostConstruct
    public void init() {
        startAsyncOperationProcessing();
    }

    public AsyncInputOperationService(OperationService operationService, OperationProperties operationProperties) {
        this.operationService = operationService;
        this.operationProperties = operationProperties;
    }

    public int getLastId() {
        return operationService.getLastId();
    }

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
//                    System.out.println("Ожидание новой операции в очереди");
                    Thread.sleep(operationProperties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation: " + operation);
                operationService.addOperation(operation);
            }
        }
    }

    public void startAsyncOperationProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }
}
