package org.example;

import java.io.Serializable;

public class OperationData implements Serializable {
    public final int MAX_SIZE = 10;
    public Operation[] operations = new Operation[MAX_SIZE];
    public Customer[] customers = new Customer[MAX_SIZE];
    public int[][] statement = new int[MAX_SIZE][];
    public int operationsSize = 0;
    public int customerSize = 0;
}
