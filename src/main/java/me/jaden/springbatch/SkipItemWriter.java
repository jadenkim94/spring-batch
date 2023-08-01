package me.jaden.springbatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class SkipItemWriter implements ItemWriter<String> {
    private int cnt;

    @Override
    public void write(List<? extends String> items) throws Exception {
        for(String item : items) {
           if(item.equals("-12")) {
               cnt++;
               throw new SkippableException("Writer failed cnt " + cnt);
           }
            System.out.println("item writer : " + item);
        }
    }
}
