package com.chetan.HibernateValidations.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

//We can use @Valid annotation in case of composition,
// so that it’s validations are executed.
public class AnotherBean {
    //Now if we validate AnotherBean instance, MyChildBean object will also be validated.
    @NotNull
    @Valid
    private MyChildBean childBean;

    //getters and setters for childBean
    public MyChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(MyChildBean childBean) {
        this.childBean = childBean;
    }
}
