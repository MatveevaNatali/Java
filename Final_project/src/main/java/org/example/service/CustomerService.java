package org.example.service;

import org.example.model.Customer;
import org.example.model.OperationData;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customers;
    private StatementService statementService;

    public CustomerService(OperationData operationData, StatementService statementService) {
        this.statementService = statementService;
        customers = operationData.customers;
    }

    public int getSize() {
        return customers.size();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomerById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public int addCustomer(String name, Date birthDt) {
        Customer customer = new Customer(customers.size(), name, birthDt);
        customers.add(customer);
        statementService.addStatementForCustomer(customer.getId());
        return customer.getId();
    }

    public void editCustomer(Customer customer) {
        Customer c = customers.stream().filter(item -> item.getId() == customer.getId()).findFirst().orElse(null);
        if (c != null) {
            c.setName(customer.getName());
            c.setBirthDt(customer.getBirthDt());
        }
    }

    public void deleteCustomer(int id) {
        customers.removeIf(c -> c.getId() == id);
        statementService.deleteStatementForCustomer(id);
    }
}
