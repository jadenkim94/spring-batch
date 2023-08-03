package me.jaden.springbatch;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.retry.support.RetryTemplate;

public class RetryItemProcessor implements ItemProcessor<String, String> {
    private int cnt;

    @Override
    public String process(String item) throws Exception {
        cnt++;
        if(item.equals("2") || item.equals("3")){
            throw new RetryableException("exception occurred");
        }
        System.out.println(item + " is processed");
        return item;
    }
}
