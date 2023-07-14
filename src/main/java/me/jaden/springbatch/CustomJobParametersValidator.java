package me.jaden.springbatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

import java.util.Objects;

public class CustomJobParametersValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        if(Objects.isNull(parameters.getString("name"))){
            throw new JobParametersInvalidException("name is null");
        }
    }
}
