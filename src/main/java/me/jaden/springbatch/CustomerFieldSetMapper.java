package me.jaden.springbatch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.util.Objects;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {
    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        if (Objects.isNull(fieldSet)) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(fieldSet.readString(0));
        customer.setAge(fieldSet.readInt(1));
        customer.setYear(fieldSet.readString(2));

        return customer;
    }
}
