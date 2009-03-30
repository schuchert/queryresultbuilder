package com.om.query;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.om.query.domain.ObjectDescription;
import com.om.query.domain.QueryResult;

public class VerifyFieldsAreIgnoredTest {
    public int getTestField() {
        return -1;
    }

    @Test
    public void canIgnoreFieldsByNameOnly() {
        QueryResultBuilder builder = new QueryResultBuilder(VerifyFieldsAreIgnoredTest.class);
        builder.ignore("testField");
        QueryResult result = builder.build(this);
        ObjectDescription objectDescription = result.get(0);
        assertNull(objectDescription.getPropertyNamed("testField"));
    }
}
