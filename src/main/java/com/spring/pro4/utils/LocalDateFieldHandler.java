package com.spring.pro4.utils;


import java.util.Properties;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class LocalDateFieldHandler extends GeneralizedFieldHandler {

    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
    }

    @Override
    public Object convertUponGet(Object value) {
        DateTimeFormatter formatters = DateTimeFormat.forPattern(dateFormatPattern);
        LocalDate localDate = (LocalDate) value;
        return localDate.toString(formatters);
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