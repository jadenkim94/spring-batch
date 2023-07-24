package me.jaden.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job8")
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
        Map params = new HashMap();
        params.put("firstname", "R%");

        return new JdbcPagingItemReaderBuilder<Customer>()
                .name("jdbcPagingReader")
                .pageSize(3)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
                .queryProvider(createQueryProvider())
                .parameterValues(params)
                .build();
    }

    private PagingQueryProvider createQueryProvider() {

        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);

        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("id, firstname, lastname, birthdate");
        queryProvider.setFromClause("from customer");
        queryProvider.setWhereClause("where firstname like :firstname");
        queryProvider.setSortKeys(sortKeys);

        try {
            return queryProvider.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ItemWriter<Customer> customItemWriter() {
        return items -> {
            System.out.println("====");
            for (Customer item : items) {
                System.out.println(item);
            }
        };
    }


}
