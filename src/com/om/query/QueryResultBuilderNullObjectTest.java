package com.om.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.om.query.domain.QueryResult;

public class QueryResultBuilderNullObjectTest {
    private QueryResultBuilder queryResultBuilder;

    @Before
    public void init() {
        queryResultBuilder = new QueryResultBuilder(Object.class);
    }

    void validateResults(QueryResult result) {
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void NullObjectResultsInEmptyList() {
        QueryResult result = queryResultBuilder.build((List<Object>) null);
        validateResults(result);
    }

    @Test
    public void NullIterableWorksSuccessfully() {
        QueryResult result = queryResultBuilder.build((Iterable<Object>) null);
        validateResults(result);
    }

    @Test
    public void NullObjectWorksSuccessfully() {
        QueryResult result = queryResultBuilder.build((Object) null);
        validateResults(result);
    }

}
