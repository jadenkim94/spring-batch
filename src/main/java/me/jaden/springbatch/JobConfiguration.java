package me.jaden.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;


@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("chunk")
                .<Customer, Customer>chunk(2)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();


    }
    @Bean
    public ItemReader<Customer> itemReader() {
        return new CustomItemReader(List.of(new Customer("jaden1"),
                new Customer("hello"),
                new Customer("hi"),
                new Customer("jaden2")));
    }

    @Bean
    public ItemProcessor itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<Customer> itemWriter() {
        return new CustomItemWriter();
    }
}
