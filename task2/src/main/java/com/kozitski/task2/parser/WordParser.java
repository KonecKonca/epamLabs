package com.kozitski.task2.parser;

import com.kozitski.task2.composite.CommonText;
import com.kozitski.task2.composite.impl.TextComponent;
import com.kozitski.task2.composite.ComponentType;
import com.kozitski.task2.composite.impl.TextLetter;
import com.kozitski.task2.composite.impl.TextSign;


public class WordParser implements TextParser {
    private static final String CHECK_LETTER_REGEXP = "\\w";

    @Override
    public CommonText parse(String word) {
        TextComponent textComponent = new TextComponent(ComponentType.WORD);

        for (int i = 0; i < word.length(); i++) {
            if(String.valueOf(word.charAt(i)).matches(CHECK_LETTER_REGEXP)){
                textComponent.addComponent(new TextLetter(word.charAt(i)));
            }
            else {
                textComponent.addComponent(new TextSign(word.charAt(i)));
            }
        }

        return textComponent;
    }



}
