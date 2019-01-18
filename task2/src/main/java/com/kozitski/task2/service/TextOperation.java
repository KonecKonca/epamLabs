package com.kozitski.task2.service;

import com.kozitski.task2.composite.CommonText;
import com.kozitski.task2.composite.impl.TextComponent;
import com.kozitski.task2.composite.ComponentType;
import com.kozitski.task2.composite.impl.TextLetter;
import com.kozitski.task2.composite.impl.TextSign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextOperation {
    private static final Logger LOGGER = LogManager.getLogger(TextOperation.class);

    public static TextComponent sortParagraphsByNumOFSentences(TextComponent text) {
            TextComponent result = new TextComponent(ComponentType.TEXT);

            if (text == null) {
                LOGGER.error("text can't be null");
                return result;
            }

            List<CommonText> paragraphs = new ArrayList<>();
            for (int i = 0; i < text.getComponentsSize(); i++) {
                paragraphs.add(text.getComponent(i));
            }
            paragraphs = paragraphs.stream().sorted(Comparator.comparingInt(CommonText::getComponentsSize)).collect(Collectors.toList());
            for (CommonText paragraph : paragraphs) {
                result.addComponent(paragraph);
            }

            LOGGER.info("Paragraphs was sorted by number of sentences");

            return result;

        }
    // All signs(TextSign.class) witch are involved into word are not counting
    public static TextComponent sortByLengthOfWordsText(TextComponent text) {
        TextComponent result = new TextComponent(ComponentType.TEXT);

        if (text == null) {
            LOGGER.error("text can't be null");
            return result;
        }

        for (int i = 0; i < text.getComponentsSize(); i++) {
            CommonText paragraph = text.getComponent(i);
            TextComponent resultParagraph = new TextComponent(ComponentType.PARAGRAPH);

            for (int j = 0; j < paragraph.getComponentsSize(); j++) {
                CommonText sentence = paragraph.getComponent(j);
                TextComponent resultSentence = new TextComponent(ComponentType.SENTENCE);

                List<TextComponent> allWords = new ArrayList<>();
                List<TextSign> allSigns = new ArrayList<>();
                for (int k = 0; k < sentence.getComponentsSize(); k++) {
                    CommonText lexeme = sentence.getComponent(k);
                    for(int l = 0; l < lexeme.getComponentsSize(); l++){
                        if(lexeme.getComponent(l) != null && lexeme.getComponent(l) instanceof TextComponent && lexeme.getComponent(l).getTypeOfTextComponent().equals(ComponentType.WORD)){
                            allWords.add((TextComponent)lexeme.getComponent(l));
                        }
                        else {
                            allSigns.add((TextSign) lexeme.getComponent(l));
                        }
                    }
                }
                allWords.sort(((o1, o2) -> {
                    int counter1 = 0;
                    int counter2 = 0;
                    for(int s = 0; s < o1.getComponentsSize(); s++){
                        if(o1.getComponent(s) instanceof TextLetter){
                            counter1++;
                        }
                    }
                    for(int s = 0; s < o2.getComponentsSize(); s++){
                        if(o2.getComponent(s) instanceof TextLetter){
                            counter2++;
                        }
                    }
                    return counter1 - counter2;
                }));


                for(TextComponent textComponent : allWords){
                    resultSentence.addComponent(textComponent);
                }
                for(TextSign sign : allSigns){
                    resultSentence.addComponent(sign);
                }
                resultParagraph.addComponent(resultSentence);
                }

                result.addComponent(resultParagraph);
            }

        LOGGER.info("Sentences was sorted by length of tokens");

        return result;

    }
    public static TextComponent reverseSortLexemesByOrderSymbol(TextComponent text, String searchSymbol){
        TextComponent result = new TextComponent(ComponentType.TEXT);

        if(text == null){
            LOGGER.error("text can't be null");
            return result;
        }


        for (int i = 0; i < text.getComponentsSize(); i++) {
            CommonText paragraph = text.getComponent(i);
            TextComponent resultParagraph = new TextComponent(ComponentType.PARAGRAPH);

            for (int j = 0; j < paragraph.getComponentsSize(); j++) {
                CommonText sentence = paragraph.getComponent(j);
                TextComponent resultSentence = new TextComponent(ComponentType.SENTENCE);

                List<CommonText> allLexemes = new ArrayList<>();
                for (int k = 0; k < sentence.getComponentsSize(); k++) {
                    allLexemes.add(sentence.getComponent(k));
                }
                allLexemes.sort(Comparator.comparing( o -> ((CommonText) o).countOfOrderedSymbol(searchSymbol))
                        .thenComparing( (e1, e2) -> ((CommonText) e2).getTextMessage().compareToIgnoreCase(((CommonText) e1).getTextMessage())));
                Collections.reverse(allLexemes);

                for(CommonText lexeme : allLexemes){
                    resultSentence.addComponent(lexeme);
                }
                resultParagraph.addComponent(resultSentence);
            }

            result.addComponent(resultParagraph);
        }

        LOGGER.info("Tokens was sorted in reverse order by number of mentioned symbols");

        return result;
    }

}













