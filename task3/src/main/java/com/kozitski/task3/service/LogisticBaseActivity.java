package com.kozitski.task3.service;


import com.kozitski.task3.entity.Wagon;
import com.kozitski.task3.exception.LogisticBaseException;

public interface LogisticBaseActivity {
    int activity(Wagon wagon) throws LogisticBaseException;
}
