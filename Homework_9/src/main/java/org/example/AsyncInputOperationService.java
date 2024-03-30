package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class AsyncInputOperationService {
    private Queue<Operation> queue = new LinkedList<>();
    private OperationService operationService;

    public AsyncInputOperationService(OperationService operationService) {
        this.operationService = operationService;
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
                    Thread.sleep(1000);
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
