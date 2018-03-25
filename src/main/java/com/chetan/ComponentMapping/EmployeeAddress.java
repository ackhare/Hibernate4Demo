package com.chetan.ComponentMapping;

import javax.persistence.*;

/**
 * Created by chetan on 25/3/18.
 */
@Embeddable
public class EmployeeAddress
{
    @Column(name="STREET")
    private String street;
    @Column(name="CITY")
    private String city;
    @Column(name="STATE")
    private String state;

    public EmployeeAddress()
    {
        super();
    }

    public EmployeeAddress(String street, String city, String state)
    {
        super();
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getStreet()
    {
        return street;
    }
    public void setStreet(String street)
    {
        this.street = street;
    }
    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "Employee_Address [street=" + street + ", city=" + city + ", state=" + state + "]";
    }
}
