package com.om.query;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.om.query.domain.ObjectDescription;
import com.om.query.domain.QueryResult;
import com.om.reflection.PropertyDoesNotExistInBeanException;

public class QueryResultBuilderTest {
    @Test
    public void NonCollectionObjectBuiltCorrectly() {
        QueryResultBuilder queryResultBuilder = new QueryResultBuilder(ClassWithAttributes.class);
        QueryResult result = queryResultBuilder.build(new ClassWithAttributes("nnn", 999));
        assertEquals(1, result.size());
        ObjectDescription objectDescription = result.get(0);
        
        String name = objectDescription.getPropertyNamed("name");
        assertEquals("nnn", name);

        String age = objectDescription.getPropertyNamed("age");
        assertEquals("999", age);
    }

    @Test
    public void IteratorConvertedCorrectly() {
        List<ClassWithAttributes> objects = new LinkedList<ClassWithAttributes>();
        objects.add(new ClassWithAttributes("aaa", 111));
        objects.add(new ClassWithAttributes("bbb", 222));
        
        QueryResultBuilder queryResultBuilder = new QueryResultBuilder(ClassWithAttributes.class);
        QueryResult result = queryResultBuilder.build(objects.iterator());
        
        assertEquals(2, result.size());
    }

    @Test(expected = PropertyDoesNotExistInBeanException.class)
    public void InvalidPropertyGeneratesExceptionAtConstruction() {
        new QueryResultBuilder(Object.class, "properyDNE");
    }

    public static class ClassWithAttributes {
        private final String name;
        private final int age;

        public ClassWithAttributes(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof ClassWithAttributes) {
                ClassWithAttributes rhs = (ClassWithAttributes) other;
                return rhs.age == age && rhs.name.equals(name);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (name + age).hashCode();
        }
    }
}
