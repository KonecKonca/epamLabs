package com.kozitski.xml.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SchemeValidator {
    public final static String XSD_PATH = "data/xsd/template.xsd";
    private final static Logger LOGGER = LogManager.getLogger(SchemeValidator.class);
    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();


    public static boolean validate(String xmlPath, String xsdPath){
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try{
            Schema schema = factory.newSchema(new File(Objects.requireNonNull(classLoader.getResource(xsdPath)).getFile()));

            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(Objects.requireNonNull(classLoader.getResource(xmlPath)).getFile()));

            validator.validate(source);

            LOGGER.info("File:" + xmlPath + " was successfully validate");
            return true;

        }
        catch (SAXException | IOException exception){
            LOGGER.error("Parse exception", exception);
            return false;
        }

    }


}