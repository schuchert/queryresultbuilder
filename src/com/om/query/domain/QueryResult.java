package com.om.query.domain;

import java.util.LinkedList;
import java.util.List;


public class QueryResult {
    private final List<ObjectDescription> objectDescriptions = new LinkedList<ObjectDescription>();

    public void add(ObjectDescription objectDescription) {
        objectDescriptions.add(objectDescription);
    }

    public List<Object> render() {
        List<Object> result = new LinkedList<Object>();

        for (ObjectDescription od : objectDescriptions)
            od.renderInto(result);

        return result;
    }

    public int size() {
        return objectDescriptions.size();
    }

    public ObjectDescription get(int index) {
        return objectDescriptions.get(index);
    }
    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("(");
        for (int i = 0; i < objectDescriptions.size(); ++i) {
            if (i > 0)
                buffer.append(", ");
            buffer.append(objectDescriptions.get(i));
        }
        buffer.append(")");

        return buffer.toString();
    }
}
