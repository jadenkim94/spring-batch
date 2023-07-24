package me.jaden.springbatch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private int id;
    private String firstname;
    private String lastname;
    private String birthdate;

}
