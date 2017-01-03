package com.chetan.HibernateFirstApplication;

/**
 * Created by chetan on 1/1/17.
 */
/*
A POJO (Plain Old Java Object) is a Java object that doesn't extend or
 implement some specialized classes and interfaces respectively required by the EJB framework.
 All normal Java objects are POJO.

When you design a class to be persisted by Hibernate,
 it's important to provide JavaBeans compliant code as well as one
  attribute which would work as index like id attribute in the Employee class.
 */
public class Employee {
   private int id;
   private String firstName;
   private String lastName;
   private int salary;

   public Employee() {}
   public Employee(String fname, String lname, int salary) {
      this.firstName = fname;
      this.lastName = lname;
      this.salary = salary;
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getFirstName() {
      return firstName;
   }
   public void setFirstName( String first_name ) {
      this.firstName = first_name;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName( String last_name ) {
      this.lastName = last_name;
   }
   public int getSalary() {
      return salary;
   }
   public void setSalary( int salary ) {
      this.salary = salary;
   }
}
