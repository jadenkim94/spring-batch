package me.jaden.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job2")
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
        return new JpaPagingItemReaderBuilder<Customer>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .queryString("select c from Customer c join fetch c.address where c.id <= 10 order by c.id")
                .build();
    }

    private ItemWriter<Customer> customItemWriter() {
        return items -> {
            System.out.println("====");
            for (Customer custoemr : items) {
                System.out.println(custoemr.getId());
                System.out.println(custoemr.getAddress().getLocation());
            }
        };
    }


}
