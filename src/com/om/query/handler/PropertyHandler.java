package com.om.query.handler;

import com.om.query.domain.ObjectDescription;
import com.om.reflection.PropertyGetter;

public abstract class PropertyHandler {
    public abstract void handle(PropertyGetter propertyDescriptor, Object targetObject,
            ObjectDescription objectDescription);
}