package com.om.query.domain;

import java.util.LinkedList;
import java.util.List;

import com.om.reflection.PropertyGetter;

public class ObjectDescription {
    private final List<PropertyDescription> propertyDescriptions = new LinkedList<PropertyDescription>();

    public void addPropertyDescription(String propertyName, int value) {
        propertyDescriptions.add(new PropertyDescription(propertyName, Integer.toString(value)));
    }

    public void addPropertyDescription(String propertyName, String value) {
        propertyDescriptions.add(new PropertyDescription(propertyName, value));
    }

    public void renderInto(List<Object> result) {
        List<Object> objectResult = new LinkedList<Object>();

        for (PropertyDescription pd : propertyDescriptions)
            pd.renderInto(objectResult);

        result.add(objectResult);

    }

    public String getPropertyNamed(String propertyName) {
        for (PropertyDescription pd : propertyDescriptions)
            if (pd.name.equals(propertyName))
                return pd.value;

        return null;
    }

    public int size() {
        return propertyDescriptions.size();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < propertyDescriptions.size(); ++i) {
            if (i > 0)
                buffer.append(", ");
            buffer.append(propertyDescriptions.get(i));
        }
        buffer.append("}");

        return buffer.toString();
    }

    public void addPropertyDescription(PropertyGetter propertyDescriptor, String value) {
        addPropertyDescription(propertyDescriptor.propertyName, value);
    }
}
