package me.jaden.springbatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SpringBatchApplicationTests {

    @Test
    void contextLoads() {
        for(int i = 2; i <= 100; i ++ ) {
            String sql = "insert into customer (id, firstname, lastname, birthdate) values (" + i + ", 'Reed', 'Edward', '2021-01-20 13:12:00');";
            System.out.println(sql);
        }

    }

}
