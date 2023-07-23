package me.jaden.springbatch;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private long id;
    private String name;
    private int age;

    public Customer(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
