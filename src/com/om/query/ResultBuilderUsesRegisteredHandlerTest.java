package com.om.query;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.om.query.domain.ObjectDescription;
import com.om.query.domain.QueryResult;
import com.om.query.handler.impl.YearMonthDayUnpaddedTranslator;

public class ResultBuilderUsesRegisteredHandlerTest {
    @Test
    public void RegisteredDateHandlerUsedForDateField() {
        QueryResultBuilder builder = new QueryResultBuilder(ClassWithDate.class);
        builder.register("date", new YearMonthDayUnpaddedTranslator());
        QueryResult listOfObjects = builder.build(new ClassWithDate());
        ObjectDescription objectDescription = listOfObjects.get(0);
        String date = objectDescription.getPropertyNamed("date");
        assertEquals("1934/9/13", date);
    }
    
    public static class ClassWithDate {
        private final Date date;

        public ClassWithDate() {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 1934);
            cal.set(Calendar.MONDAY, Calendar.SEPTEMBER);
            cal.set(Calendar.DATE, 13);
            date = cal.getTime();
        }

        public Date getDate() {
            return date;
        }
    }
}
