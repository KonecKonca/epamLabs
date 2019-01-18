package com.kozitski.triangle.register;

@FunctionalInterface
public interface Observer<T>{
    void handleEvent(T t);
}
