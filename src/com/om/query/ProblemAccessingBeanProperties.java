package com.om.query;

public class ProblemAccessingBeanProperties extends RuntimeException {
    public final Class<?> clazz;

    public ProblemAccessingBeanProperties(Class<?> clazz, Exception e) {
        super(e);
        this.clazz = clazz;
    }

    private static final long serialVersionUID = 1L;

}
