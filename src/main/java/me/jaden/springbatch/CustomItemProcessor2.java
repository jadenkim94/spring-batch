package me.jaden.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor2 implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        customer.setUsername(customer.getUsername().toUpperCase());
        return customer;
    }
}
