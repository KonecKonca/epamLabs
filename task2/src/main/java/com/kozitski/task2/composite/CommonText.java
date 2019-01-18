package com.kozitski.task2.composite;

public interface CommonText {

    String getTextMessage();
    boolean addComponent(CommonText commonText);
    CommonText getComponent(int index);
    int getComponentsSize();
    ComponentType getTypeOfTextComponent();
    int countOfOrderedSymbol(String symbol);

}
