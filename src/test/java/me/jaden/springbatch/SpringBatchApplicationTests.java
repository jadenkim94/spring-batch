package me.jaden.springbatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SpringBatchApplicationTests {

    @Test
    void contextLoads() {
        Client client = new Client();
        client.print();
    }

}
