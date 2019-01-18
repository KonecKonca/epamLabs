package com.kozitski.xml.validator;

import com.kozitski.xml.validator.SchemeValidator;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class SchemeValidatorTest {
    private static final String INCORRECT_XML_PATH = "data/incorrect.xml";
    private static final String CORRECT_XML_PATH = "data/correct.xml";
    private static final String DATA_FOR_FILE_NOT_FOUND = "data/incorrect.xml!!!";

    @Test
    public void testValidateTrue() {

        boolean actual = true;
        boolean expected = SchemeValidator.validate(CORRECT_XML_PATH, SchemeValidator.XSD_PATH);
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateFalse() {
        boolean actual = true;
        boolean expected = SchemeValidator.validate(INCORRECT_XML_PATH, SchemeValidator.XSD_PATH);

        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testValidateFileNotFound() {
        SchemeValidator.validate(DATA_FOR_FILE_NOT_FOUND , SchemeValidator.XSD_PATH);
    }

}