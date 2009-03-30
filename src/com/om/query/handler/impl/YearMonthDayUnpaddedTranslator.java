package com.om.query.handler.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class YearMonthDayUnpaddedTranslator extends PropertyHandler {
    static final DateFormat format = new SimpleDateFormat("yyyy/M/d");

    @Override
    public void handle(PropertyGetter pg, Object targetObject, ObjectDescription objectDescription) {
        Object propertyValue = pg.getValue(targetObject);

        String formattedValue = "";

        if (propertyValue != null) {
            formattedValue = format.format(propertyValue);
        }

        objectDescription.addPropertyDescription(pg.propertyName, formattedValue);
    }
}
