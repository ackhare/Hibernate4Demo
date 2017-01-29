package com.chetan.HibernateValidations.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParamValidationBean {
//We can define constraints for method parameters too,
	private String name;
	
	//using @NotNull at constructor rather than at field
	public ParamValidationBean(@NotNull String name){
		this.name = name;
	}
	
	public void printData(@NotNull @Size(min=5) String data){
		System.out.println("Data is::"+data);
	}

	public String getName() {
		return name;
	}

}
