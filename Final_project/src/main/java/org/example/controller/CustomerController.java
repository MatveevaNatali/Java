package org.example.controller;

import org.example.model.Customer;
import org.example.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        int id = customerService.addCustomer(customer.getName(), customer.getBirthDt());
        return ResponseEntity.ok("Создан пользователь с id=" + id);
    }

    @PutMapping
    public ResponseEntity<?> editCustomer(@RequestBody Customer customer) {
        customerService.editCustomer(customer);
        return ResponseEntity.ok("Пользователь изменён");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Пользователь удалён");
    }
}
