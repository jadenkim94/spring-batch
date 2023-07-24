package me.jaden.springbatch;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
