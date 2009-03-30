package com.om.query.handler.impl;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import com.om.query.domain.ObjectDescription;
import com.om.reflection.PropertyGetter;
import com.om.reflection.ReflectionUtil;

public class YearMonthDayUnpaddedTranslatorTest {
    @Test
    public void ValidateTranslation() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1789);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DATE, 9);

        ObjectDescription od = new ObjectDescription();
        PropertyGetter getter = ReflectionUtil.getPropertyGetterNamed(Calendar.class, "time");
        new YearMonthDayUnpaddedTranslator().handle(getter, cal, od);
        String time = od.getPropertyNamed("time");
        assertEquals("1789/3/9", time);
    }
}
