package me.jaden.springbatch;

public class CustomService<T> {

    public void customWrite(T item){
        System.out.println(item);
    }
}
