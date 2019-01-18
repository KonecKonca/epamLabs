package com.kozitski.task2.parser;

import com.kozitski.task2.composite.impl.TextComponent;
import com.kozitski.task2.composite.ComponentType;
import com.kozitski.task2.composite.impl.TextSign;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    private static final String WORD_REGEXP = "[\\w)('][\\w-)(']*";
    private WordParser wordParser = new WordParser();

    @Override
    public TextComponent parse(String lexeme) {
        TextComponent textComponent = new TextComponent(ComponentType.LEXEME);

        Pattern pattern = Pattern.compile(WORD_REGEXP);
        Matcher matcher = pattern.matcher(lexeme);
        String word = "";
        while(matcher.find()){
            word = matcher.group();
            textComponent.addComponent(wordParser.parse(word));
        }
        if((lexeme.length() - word.length()) == 1){
           textComponent.addComponent(new TextSign(lexeme.charAt(lexeme.length() - 1)));
        }

        return textComponent;
    }

}
