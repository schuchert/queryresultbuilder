package com.om.query.handler.impl;

import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class IgnoreFieldHandler extends PropertyHandler {

    @Override
    public void handle(PropertyGetter propertyDescriptor, Object targetObject, ObjectDescription objectDescription) {
    }

}
