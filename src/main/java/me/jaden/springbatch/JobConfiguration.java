package me.jaden.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                .incrementer(new RunIdIncrementer())
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
                new Customer(2, "user8", 30),
                new Customer(3, "user3", 31),
                new Customer(4, "user4", 23),
                new Customer(5, "user5", 28),
                new Customer(6, "user6", 36)));
    }

    private ItemWriter<Customer> customItemWriter() {
        ItemWriterAdapter<Customer> writer = new ItemWriterAdapter<>();
        writer.setTargetObject(new CustomService<Customer>());
        writer.setTargetMethod("customWrite");
        return writer;
    }
}
