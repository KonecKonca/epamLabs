package com.kozitski.task3.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.kozitski.task3.util.parameter.ProductParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductValidator {
    private static final Logger LOGGER = LogManager.getLogger(ProductValidator.class);
    private static final String PRODUCT_VALIDATION_PATH = "data/validation/product.yml";

    public boolean validate(String name, BigDecimal price){
        if(name == null || price == null){
            return false;
        }

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        ProductParameter productParameter;
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            productParameter = mapper.readValue(new File(Objects.requireNonNull(loader.getResource(PRODUCT_VALIDATION_PATH)).getFile()), ProductParameter.class);

        } catch (IOException e) {
            LOGGER.error("Property file for product validation is not exist", e);
            return false;
        }

        int nameMinSize = productParameter.getNameMinSize();
        int nameMaxSize = productParameter.getNameMaxSize();

        double minPrice = productParameter.getMinPrice();
        double maxPrice = productParameter.getMaxPrice();

        name = name.trim();

        return name.length() >= nameMinSize && name.length() <= nameMaxSize && !name.isEmpty() &&
                price.doubleValue() >= minPrice && price.doubleValue() <= maxPrice;

    }

}
