package com.om.query;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.om.query.domain.ObjectDescription;
import com.om.query.domain.QueryResult;
import com.om.query.handler.PropertyHandler;
import com.om.query.handler.impl.DefaultNullToEmptyStringHandler;
import com.om.query.handler.impl.IgnoreFieldHandler;
import com.om.reflection.ObjectPropertyGetters;
import com.om.reflection.PropertyGetter;
import com.om.reflection.ReflectionUtil;

public class QueryResultBuilder {
    private static final Map<Class<?>, PropertyHandler> globalTypeBasedPropertyHandlers = new ConcurrentHashMap<Class<?>, PropertyHandler>();
    public static final PropertyHandler defaultNullToEmptyStringTranslator = new DefaultNullToEmptyStringHandler();
    public static final PropertyHandler ignoreFieldHandler = new IgnoreFieldHandler();

    private final Map<PropertyGetter, PropertyHandler> handlers = new ConcurrentHashMap<PropertyGetter, PropertyHandler>();
    private final Class<?> targetType;

    public QueryResultBuilder(Class<?> clazz, String... propertiesToTranslate) {
        this.targetType = clazz;
        registerHandlerForEach(propertiesToTranslate);
    }

    private void registerHandlerForEach(String... propertiesToTranslate) {
        ReflectionUtil.validateAllPropertiesExistIn(propertiesToTranslate, targetType);

        for (String s : propertiesToTranslate) {
            register(s, defaultNullToEmptyStringTranslator);
        }
    }

    public QueryResultBuilder(Class<?> clazz) {
        this.targetType = clazz;

        ObjectPropertyGetters getters = ReflectionUtil.getPropertyGettersFor(clazz);

        for (PropertyGetter pg : getters)
            register(pg, defaultNullToEmptyStringTranslator);
    }

    public QueryResult build(Iterable<?> iterable) {
        if (iterable == null)
            return build((Object) null);

        return build(iterable.iterator());
    }

    public QueryResult build(Iterator<?> iterator) {
        QueryResult result = new QueryResult();
        describeEachObjectInto(iterator, result);
        return result;
    }

    private void describeEachObjectInto(Iterator<?> iterator, QueryResult result) {
        while (iterator != null && iterator.hasNext())
            describeObjectInto(iterator.next(), result);
    }

    public QueryResult build(Object object) {
        QueryResult result = new QueryResult();
        if (object != null)
            describeObjectInto(object, result);
        return result;
    }

    private void describeObjectInto(Object bean, QueryResult result) {
        if (bean == null)
            return;

        ObjectDescription objectDescription = new ObjectDescription();

        for (Entry<PropertyGetter, PropertyHandler> entry : handlers.entrySet())
            entry.getValue().handle(entry.getKey(), bean, objectDescription);

        result.add(objectDescription);
    }

    private void register(PropertyGetter pg, PropertyHandler providedPropertyHandler) {
        PropertyHandler handler = globalTypeBasedPropertyHandlers.get(pg.getPropertyType());
        if (handler == null)
            handler = providedPropertyHandler;
        
        handlers.put(pg, handler);
    }

    public void register(String propertyName, PropertyHandler propertyHandler) {
        PropertyGetter pg = ReflectionUtil.getPropertyGetterNamed(targetType, propertyName);
        handlers.put(pg, propertyHandler);
    }

    public void ignore(String property) {
        register(property, ignoreFieldHandler);
    }

    public static void addDefaultTypeHandler(Class<?> clazz, PropertyHandler handler) {
        globalTypeBasedPropertyHandlers.put(clazz, handler);
    }
}
