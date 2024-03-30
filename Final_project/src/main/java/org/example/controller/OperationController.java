package org.example.controller;

import org.example.model.Operation;
import org.example.service.AsyncInputOperationService;
import org.example.service.OperationService;
import org.example.service.StatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationController {
    private final OperationService operationService;
    private final StatementService statementService;
    private final AsyncInputOperationService asyncInputOperationService;

    public OperationController(OperationService operationService,
                               StatementService statementService,
                               AsyncInputOperationService asyncInputOperationService) {
        this.operationService = operationService;
        this.statementService = statementService;
        this.asyncInputOperationService = asyncInputOperationService;
    }

    @GetMapping("/{customer-id}")
    public List<Operation> getOperationsByCustomerId(@PathVariable("customer-id") int id) {
        return statementService.getOperationByCustomer(id);
    }

    @PostMapping("/{customer-id}")
    public ResponseEntity<?> addOperation(@PathVariable("customer-id") int id, @RequestBody Operation operation) {
        operation.setId(operationService.getLastId());
        asyncInputOperationService.offerOperation(operation);
        statementService.addStatement(id, operation);
        return ResponseEntity.ok("Операция добавлена");
    }

    @DeleteMapping("/{customer-id}/{operation-id}")
    public ResponseEntity<?> deleteOperation(@PathVariable("customer-id") int customerId, @PathVariable("operation-id") int operationId) {
        operationService.deleteOperation(operationId);
        statementService.deleteStatement(customerId, operationId);
        return ResponseEntity.ok("Операция удалена");
    }
}
