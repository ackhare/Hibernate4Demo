package HibernateManyToOne;

/**
 * Created by chetan on 1/1/17.
 */
import java.util.*;

public class Employee{
   private int id;
   private String firstName;
   private String lastName;
   private int salary;
   private Address address;
//two constructor including default copnstructor
   public Employee() {}
   //Address is inducted here
   public Employee(String fname, String lname,
                   int salary, Address address ) {
      this.firstName = fname;
      this.lastName = lname;
      this.salary = salary;
      this.address = address;
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

   public Address getAddress() {
      return address;
   }
   public void setAddress( Address address ) {
      this.address = address;
   }
}
