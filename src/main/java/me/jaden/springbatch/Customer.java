package me.jaden.springbatch;

import lombok.Data;

@Data
public class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public void upperName() {
        this.name = name.toUpperCase();
    }

}
