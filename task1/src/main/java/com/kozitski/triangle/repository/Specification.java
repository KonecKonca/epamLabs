package com.kozitski.triangle.repository;

@FunctionalInterface
public interface Specification<T>{
    boolean specify(T t);
}
