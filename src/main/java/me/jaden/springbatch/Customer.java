package me.jaden.springbatch;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private String name;
    private int age;
    private String year;

    public Customer(String name, int age, String year) {
        this.name = name;
        this.age = age;
        this.year = year;
    }
}
