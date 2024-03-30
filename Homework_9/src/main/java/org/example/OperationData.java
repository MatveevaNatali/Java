package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationData implements Serializable {
    public Map<Integer, Operation> operations = new HashMap<>();
    public List<Customer> customers = new ArrayList<>();
    public Map<Integer, List<Operation>> statement = new HashMap<>();
}
