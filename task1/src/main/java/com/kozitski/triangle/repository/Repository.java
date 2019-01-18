package com.kozitski.triangle.repository;

import java.util.List;

public interface Repository<T> {

    void add(T t);
    void remove(T t);
    void removeAll();
    T get(int index);
    List<T> query(Specification<T> specification);

}
