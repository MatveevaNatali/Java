package org.example;

import java.util.Date;
import java.util.List;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService(OperationData operationData) {
        customers = operationData.customers;
    }

    public int getSize() {
        return customers.size();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public int addCustomer(String name, Date birthDt) {
        customers.add(new Customer(customers.size(), name, birthDt));
        return customers.size() - 1;
    }
}
