package com.om.query;

public class ProblemAccessingBeanException extends RuntimeException {
    public final Object bean;

    public ProblemAccessingBeanException(Object bean, Exception e) {
        super(e);
        this.bean = bean;
    }

    private static final long serialVersionUID = 1L;

}
