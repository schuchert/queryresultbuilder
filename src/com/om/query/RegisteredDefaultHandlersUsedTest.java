package com.om.query;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.om.query.domain.ObjectDescription;
import com.om.query.domain.QueryResult;
import com.om.query.handler.PropertyHandler;
import com.om.query.handler.impl.DefaultNullToEmptyStringHandler;
import com.om.reflection.PropertyGetter;

public class RegisteredDefaultHandlersUsedTest {
    @BeforeClass
    public static void registerGlobalHandler() {
        QueryResultBuilder.addDefaultTypeHandler(String.class, new ReverseStringHandler());
    }

    @Test
    public void globalTypeBasedPropertyHandlerUsedDuringConstruction() {
        QueryResultBuilder builder = new QueryResultBuilder(ClassWithProperties.class);
        QueryResult result = builder.build(new ClassWithProperties());
        assertEquals("hello world", result.get(0).getPropertyNamed("b"));
    }

    public void globalTypeBasedPropertyHandlerOverriddenByRegsiterMethod() {
        QueryResultBuilder builder = new QueryResultBuilder(ClassWithProperties.class);
        builder.register("b", new DefaultNullToEmptyStringHandler());
        QueryResult result = builder.build(new ClassWithProperties());
        assertEquals("HELLO WORLD", result.get(0).getPropertyNamed("b"));
    }

    public static class ReverseStringHandler extends PropertyHandler {

        @Override
        public void handle(PropertyGetter propertyDescriptor, Object targetObject, ObjectDescription objectDescription) {
            String value = propertyDescriptor.getValue(targetObject, String.class);
            objectDescription.addPropertyDescription(propertyDescriptor, value.toLowerCase());
        }
    }

    public static class ClassWithProperties {
        private int a;
        private String b;

        public ClassWithProperties() {
            a = 42;
            b = "HELLO WORLD";
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
