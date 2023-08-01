package me.jaden.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class SkipItemProcessor implements ItemProcessor<String, String> {
    private int cnt;
    @Override
    public String process(String item) throws Exception {
        if(item.equals("6") || item.equals("7")) {
            cnt++;
            throw new SkippableException("process failed count : " + cnt);
        }
        System.out.println("ItempProcess : " + item);
        return String.valueOf(Integer.parseInt(item) * -1);
    }
}
