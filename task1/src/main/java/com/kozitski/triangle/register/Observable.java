package com.kozitski.triangle.register;

// I am don't sure that parametrization is necessary here
public interface Observable<T extends Observer> {
    void addObserver(T observer);
    void removeObserver(T observer);
    void notifyObservers();
}

