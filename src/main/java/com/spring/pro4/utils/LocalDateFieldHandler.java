package com.spring.pro4.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;


public class LocalDateFieldHandler extends GeneralizedFieldHandler {

    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
    }

    @Override
    public Object convertUponGet(Object value) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(dateFormatPattern);
        LocalDate localDate = (LocalDate) value;
        return localDate.format(formatters);
    }

    @Override
    public Object convertUponSet(Object value) {
        String dateStr = (String) value;
        return LocalDate.parse(dateStr);
    }

    @Override
    public Class getFieldType() {
        return LocalDate.class;
    }
}