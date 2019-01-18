package com.kozitski.xml.exception;

public class XMLParseException extends Exception{

    public XMLParseException() {
        super();
    }
    public XMLParseException(String message) {
        super(message);
    }
    public XMLParseException(String message, Throwable cause) {
        super(message, cause);
    }
    public XMLParseException(Throwable cause) {
        super(cause);
    }

}
