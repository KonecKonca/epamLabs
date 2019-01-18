package com.kozitski.xml.builder;

public enum TariffXsdElement {

    TARIFFS,
    LIMIT_TARIFF,
    UNLIMIT_TARIFF,
    NAME,
    OPERATOR_NAME,
    SMS_PRICE,
    PARAMETERS,
    FAVOURITE_NUMBER,
    TARIFFICATION,
    PAY_FOR_CONNECTION,
    PRICE_IN_MINUTE_EXTERNAL_CALLS,
    PRICE_IN_MINUTE_INNER_CALLS,
    PRICE_FOR_MB_INTERNET,
    PAYROLL,
    IS_EXTERNAL_CALLS,
    IS_INNER_CALLS,
    IS_INTERNET;

    // instead common lang.Enum method, without Exception
    public static boolean containsElement(String test) {

        for (TariffXsdElement element : TariffXsdElement.values()) {
            if (element.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

}
