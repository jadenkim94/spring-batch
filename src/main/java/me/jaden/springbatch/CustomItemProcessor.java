package me.jaden.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        customer.setAge(customer.getAge()+1);
        return customer;
    }
}
