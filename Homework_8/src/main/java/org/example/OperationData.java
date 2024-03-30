package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OperationData implements Serializable {
    public List<Operation> operations = new ArrayList<>();
    public List<Customer> customers = new ArrayList<>();
    public List<List<Integer>> statement = new ArrayList<>();
}
