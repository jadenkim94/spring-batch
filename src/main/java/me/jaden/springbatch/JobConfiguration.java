package me.jaden.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final String ANY_STATUS = "*";

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .start(step1(null))
                .next(step2())
                .listener(new JobListener())
                .build();
    }

    @Bean
    @JobScope
    public Step step1(@Value("#{jobParameters['message']}") String message) {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("message --> " + message);
                    System.out.println(contribution.getStepExecution().getStepName() + " has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(tasklet(null, null, null))
                .listener(new CustomStepExecutionListener())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet tasklet(@Value("#{jobExecutionContext['name']}") String name,
                           @Value("#{stepExecutionContext['name2']}") String name2,
                           @Value("#{jobParameters['message']}") String message) {
        return (stepContribution, chunkContext) -> {
            System.out.println("name --> " + name);
            System.out.println("name2 --> " + name2);
            System.out.println("message --> " + message);
            System.out.println("tasklet has executed");
            return RepeatStatus.FINISHED;
        };
    }
}
