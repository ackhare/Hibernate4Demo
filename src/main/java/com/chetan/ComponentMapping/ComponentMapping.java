package com.chetan.ComponentMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ComponentMapping
{
    public static void main(String args[])
    {
        //Reading the hibernate configuration file
        Configuration configuration = new Configuration().configure("com/chetan/ComponentMapping/hibernate.cfg.xml");
        StandardServiceRegistryBuilder regBuilber = new StandardServiceRegistryBuilder();
        regBuilber.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = regBuilber.build();

        //Create SessionFacctory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        //Create Session from SessionFactory
        Session session = sessionFactory.openSession();

        //Begin the transaction
        session.beginTransaction();

        //Create a new EmployeeAddress object
        EmployeeAddress address = new EmployeeAddress();
        address.setStreet("Tharamani");
        address.setCity("Chennai");
        address.setState("TamilNadu");

        //Create a new Employee object
        Employee employee = new Employee();
        //employee.setId(1);
        employee.setName("JavaInterviewPoint");
        employee.setAddress(address);

        session.save(employee);

        //Commit the changes
        session.getTransaction().commit();
        //Close the session
        session.close();
    }
}
