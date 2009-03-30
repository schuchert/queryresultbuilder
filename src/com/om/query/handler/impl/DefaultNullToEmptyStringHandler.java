package com.om.query.handler.impl;

import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class DefaultNullToEmptyStringHandler extends PropertyHandler {

    @Override
    public void handle(PropertyGetter pg, Object targetObject, ObjectDescription objectDescription) {
        Object propertyValue = pg.getValue(targetObject);

        String formattedValue = "";

        if (propertyValue != null)
            formattedValue = propertyValue.toString();

        objectDescription.addPropertyDescription(pg.propertyName, formattedValue);
    }
}
