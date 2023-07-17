package me.jaden.springbatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getExecutionContext().put("name", "user1");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
