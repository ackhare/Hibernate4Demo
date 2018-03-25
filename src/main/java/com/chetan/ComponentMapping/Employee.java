package com.chetan.ComponentMapping;

import javax.persistence.*;

/**
 * Created by chetan on 25/3/18.
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee
{
    @Id
    @GeneratedValue
    @Column(name="EMP_ID")
    private int id;
    @Column(name="EMP_NAME")
    private String name;

    @Embedded
    private EmployeeAddress address;

    public Employee()
    {
        super();
    }

    public Employee(int id, String name, EmployeeAddress address)
    {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public EmployeeAddress getAddress()
    {
        return address;
    }

    public void setAddress(EmployeeAddress address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "Employee [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
}
