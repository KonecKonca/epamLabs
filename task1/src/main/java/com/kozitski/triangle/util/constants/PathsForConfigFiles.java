package com.kozitski.triangle.util.constants;

public enum PathsForConfigFiles {
    VALIDATION_CONFIGURATIONS("src/main/resources/config/validationConfigurations.properties"),
    SEARCH_CONFIGURATIONS("src/main/resources/config/searchConfig.properties");

    private String path;

    PathsForConfigFiles(String value) {
        this.path = value;
    }
    public String getPath(){
        return path;
    }
}
