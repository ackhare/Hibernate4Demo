package com.chetan.HibernateValidations.model;

import javax.validation.constraints.NotNull;
//We can apply constraints on the getter methods too,
// we should not apply it on setter method.
// Also we should avoid applying constraints on
// both fields and getter method because then it will be validated twice.
public class MyBean {

	private String name;

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
