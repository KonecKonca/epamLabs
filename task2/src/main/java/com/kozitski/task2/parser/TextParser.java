package com.kozitski.task2.parser;

import com.kozitski.task2.composite.CommonText;

@FunctionalInterface
public interface TextParser {
    CommonText parse(String text);
}
