package me.jaden.springbatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class CustomClassifier<C,T> implements Classifier<C, T> {

    private Map<Integer, ItemProcessor> processorMap = new HashMap<>();

    public CustomClassifier() {
        this.processorMap.put(1, new CustomItemProcessor());
        this.processorMap.put(2, new CustomItemProcessor2());
    }

    @Override
    public T classify(C classifiable) {
        int check = ((Customer) classifiable).getId();
        return (T)processorMap.get((check%2)+1);
    }

    public void setProcessorMap(Map<Integer, ItemProcessor> processorMap) {
        this.processorMap = processorMap;
    }
}
