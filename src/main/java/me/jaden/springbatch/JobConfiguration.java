package me.jaden.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
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
                .start(step1())
                    .on(ExitStatus.FAILED.getExitCode())
                    .to(step2())
                    .on(ExitStatus.FAILED.getExitCode())
                    .stop()
                .from(step1())
                    .on(ANY_STATUS)
                    .to(step3())
                    .next(step4())
                .from(step2())
                    .on(ANY_STATUS)
                    .to(step2())
                    .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step 1 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step 2 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(contribution.getStepExecution().getStepName() + " has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(contribution.getStepExecution().getStepName() + " has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(contribution.getStepExecution().getStepName() + " has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
