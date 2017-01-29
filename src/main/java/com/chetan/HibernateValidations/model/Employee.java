package com.chetan.HibernateValidations.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;

public class Employee {

	//This will be checked in main class through
	//validationErrors = validator.validate(emp, EmpIdCheck.class);
    //where the below group will be passed
	@Min(value=1, groups=EmpIdCheck.class)
	private int id;
	
	@NotNull(message="Name cannot be null")
	@Size(min=5, max=30)
	private String name;
//	@Email and @CreditCardNumber are hiberenate specfic



	@Email
	private String email;
	
	@CreditCardNumber
	private String creditCardNumber;
	
	//default no-args constructor
	public Employee(){}
	
	public Employee(int id, String name, String email, String ccNum){
		this.id=id;
		this.name=name;
		this.email=email;
		this.creditCardNumber=ccNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
}
