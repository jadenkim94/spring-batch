package me.jaden.springbatch;

public class SkippableException extends RuntimeException {
    public SkippableException(String message) {
        super(message);
    }
}
