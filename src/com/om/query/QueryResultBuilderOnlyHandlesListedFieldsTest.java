package com.om.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.om.query.domain.ObjectDescription;
import com.om.query.domain.QueryResult;

public class QueryResultBuilderOnlyHandlesListedFieldsTest {

    @Test
    public void OnlyFieldsListTranslated() {
        QueryResultBuilder builder = new QueryResultBuilder(LotsOfFields.class, "a", "d");
        QueryResult result = builder.build(new LotsOfFields());
        ObjectDescription objectDescription = result.get(0);
        assertEquals(2, objectDescription.size());
        String a = objectDescription.getPropertyNamed("a");
        String d = objectDescription.getPropertyNamed("d");
        assertEquals("AAA", a);
        assertNotNull("DDD", d);
    }

    public static class LotsOfFields {
        public LotsOfFields() {
            a = "AAA";
            b = "BBB";
            c = "CCC";
            d = "DDD";
        }
        
        public String getA() {
            return a;
        }

        public String getB() {
            return b;
        }

        public String getC() {
            return c;
        }

        public String getD() {
            return d;
        }

        String a;
        String b;
        String c;
        String d;
    }
}
