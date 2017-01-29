package com.chetan.HibernateValidations.model;

import javax.validation.constraints.NotNull;
//Bean validations are inherited, so if
// we are validating object of child class then
// any validations in parent class will also be executed.
public class MyChildBean extends MyBean {
//If we will validate instance of MyChildBean, MyBean name field will also be validated.
	private String data;

	@NotNull
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
