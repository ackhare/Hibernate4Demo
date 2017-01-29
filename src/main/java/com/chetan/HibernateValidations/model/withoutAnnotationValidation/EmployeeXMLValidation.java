package com.chetan.HibernateValidations.model.withoutAnnotationValidation;

//Sometimes we want validation on third party classes, then we can’t use annotations with them.
// In this situation, xml configuration based validation comes handy.
// For example, let’s say we have a class without any validation annotations like below.
public class EmployeeXMLValidation {

    private int id;

    private String name;

    private String email;

    private String creditCardNumber;

    //default no-args constructor
    public EmployeeXMLValidation() {
    }

    public EmployeeXMLValidation(int id, String name, String email, String ccNum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.creditCardNumber = ccNum;
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
