package me.jaden.springbatch;

public class Client {

    Person person = new Person<>("hello");
    Person person2 = new Person<>(123);

    public void print() {
        person.printInfo();
        person2.printInfo();
    }
}
