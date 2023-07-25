package me.jaden.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import java.util.List;


@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job6")
                .start(step1())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("chunk")
                .<Customer, Customer>chunk(5)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader customItemReader() {
        return new ListItemReader(List.of(new Customer(1, "user1", 20),
                new Customer(2, "user2", 30),
                new Customer(3, "user3", 31),
                new Customer(4, "user4", 23),
                new Customer(5, "user5", 28),
                new Customer(6, "user6", 36)));
    }

    private ItemWriter<Customer> customItemWriter() {
        return new FlatFileItemWriterBuilder<Customer>()
                .name("flatFileItemWriter")
                .resource(new FileSystemResource("/Users/jaden/Desktop/study/spring-batch/src/main/resources/customer.txt"))
                .delimited()
                .delimiter("|")
                .names("id", "username", "age")
                .append(true)
                .build();
    }
}
