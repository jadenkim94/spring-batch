package me.jaden.springbatch;

public class Person<T> {
    private T info;

    public Person(T info) {
        this.info = info;
    }

    public void printInfo() {
        System.out.println(info);
    }
}


