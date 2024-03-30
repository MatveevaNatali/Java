package org.example;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Customer implements Serializable {
    private long id;                // Id клиента
    private String name;            // Полное имя
    private Date birthDt;           // Дата рождения

    public Customer() {
    }

    public Customer(long id, String name, Date birthDt) {
        this.id = id;
        this.name = name;
        this.birthDt = birthDt;
    }
}
