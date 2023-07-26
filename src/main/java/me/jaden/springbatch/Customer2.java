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
public class Customer2 {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private int age;

    public static Customer2 createCustomer2(Customer customer) {
        return new Customer2(customer.getId(), customer.getUsername(), customer.getAge());
    }

}
