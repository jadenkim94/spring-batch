package me.jaden.springbatch;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LimitCheckingItemSkipPolicy implements SkipPolicy {

    Map<Class<? extends Throwable>, Boolean> exceptionClasses = new HashMap<>();

    public LimitCheckingItemSkipPolicy(Class<? extends Throwable>...exceptions) {
        Arrays.stream(exceptions).forEach(exception -> exceptionClasses.put(exception ,true));
    }

    @Override
    public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {

        return false;
    }
}
