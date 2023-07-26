package me.jaden.springbatch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private int age;

    public Customer2 convertToCustomer2() {
        return new Customer2(id, username, age);
    }
}
