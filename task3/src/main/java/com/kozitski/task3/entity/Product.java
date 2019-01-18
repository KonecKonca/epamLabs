package com.kozitski.task3.entity;

import com.kozitski.task3.util.generator.ProductIdGenerator;
import com.kozitski.task3.validator.ProductValidator;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private long productId;
    private String name;
    private BigDecimal price;
    private ProductType type;


    public Product(String name, BigDecimal price, ProductType type) {
        this(name, price);
        this.type = type;
    }
    public Product(String name, BigDecimal price) {
        ProductValidator validator = new ProductValidator();

        if(validator.validate(name, price)){
            this.name = name;
            this.price = price;
            type = ProductType.NOT_PERISHABLE;
            productId = ProductIdGenerator.generateId();
        }
        else {
            throw new RuntimeException("Product can not be created, validation is failed");
        }
    }

    public long getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public ProductType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getName(), product.getName()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                getType() == product.getType();
    }
    @Override
    public int hashCode() {
        int hashCode = 0;

        hashCode = Objects.hashCode(name);
        hashCode += (int) (31 * price.doubleValue());
        hashCode += 31 * Objects.hashCode(type);

        return hashCode;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Product: ");

        stringBuilder.append("id - ");
        stringBuilder.append(productId);
        stringBuilder.append(", ");

        stringBuilder.append("name - ");
        stringBuilder.append(name);
        stringBuilder.append(", ");

        stringBuilder.append("price - ");
        stringBuilder.append(price.doubleValue());
        stringBuilder.append(", ");

        stringBuilder.append("type - ");
        stringBuilder.append(type.name());

        return stringBuilder.toString();
    }

}
