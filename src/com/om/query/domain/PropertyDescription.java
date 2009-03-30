package com.om.query.domain;

import java.util.LinkedList;
import java.util.List;

public class PropertyDescription {
    public final String name;
    public final String value;

    public PropertyDescription(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void renderInto(List<Object> objectResult) {
        List<String> myDescription = new LinkedList<String>();

        myDescription.add(name);
        myDescription.add(value);

        objectResult.add(myDescription);
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %s]", name, value);
    }
}
