package me.jaden.springbatch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id @GeneratedValue
    private int id;
    private String username;
    private String age;
    @OneToOne(mappedBy = "customer")
    private Address address;

}
