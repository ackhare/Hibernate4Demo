package com.chetan.HibernateValidations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import com.chetan.HibernateValidations.model.*;
import com.chetan.HibernateValidations.model.withoutAnnotationValidation.EmployeeXMLValidation;
import org.hibernate.validator.HibernateValidator;
/*
Important points from above hibernate validator test program are:
1. Validator instance is thread safe, so we can cache it and reuse it.
2. If there are multiple JSR303 implementation present, then we can get the Hibernate Validator
instance using Validation.byProvider() method.
3. Notice the use of validation of a group, it’s validating only the fields that are part of the group.
4. ExecutableValidator is used to validate the parameters of a method.
5. ExecutableValidator provide methods to validate constructor parameters and return values too, these methods are validateReturnValue(), validateConstructorParameters() and validateConstructorReturnValue()
Hibernate Validator Custom Validation and Spring Framework integration
We can create our own custom validator too, please read Spring Hibernate Validator Example for better understanding of this feature.
 */


public class ValidatorTest {

	public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException, SecurityException {
		
		//Getting Validator instance with Annotations
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		//If there are multiple JSR303 implementations in classpath
		//we can get HibernateValidator specifically too
		ValidatorFactory hibernateVF = Validation.byProvider(HibernateValidator.class)
									.configure().buildValidatorFactory();
		
		System.out.println("\nSimple field level validation example");
        //Validating a Employee (Annotated) class-------start
		Employee emp = new Employee(-1, "Name","email","123");
		Set<ConstraintViolation<Employee>> validationErrors = validator.validate(emp);
		
		if(!validationErrors.isEmpty()){
			for(ConstraintViolation<Employee> error : validationErrors){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
		//Validating a Employee (Annotated) class-------end
		System.out.println("\nXML Based validation example");
		
		//Validating a Employee XML Based validation --------- start
		Configuration<?> config = Validation.byDefaultProvider().configure();
		config.addMapping(new FileInputStream("/home/chetan/IdeaProjects/HibernateResourrces/src/main/resources/com/chetan/HibernateValidations/employeeXMLValidation.xml"));
		ValidatorFactory validatorFactory1 = config.buildValidatorFactory();
		Validator validator1 = validatorFactory1.getValidator();
		
		EmployeeXMLValidation emp1 = new EmployeeXMLValidation(10, "Name","email","123");
		
		Set<ConstraintViolation<EmployeeXMLValidation>> validationErrors1 = validator1.validate(emp1);
		
		if(!validationErrors1.isEmpty()){
			for(ConstraintViolation<EmployeeXMLValidation> error : validationErrors1){
				System.out.println(error.getMessageTemplate()+"::"+error.getInvalidValue()+"::"+ error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
        //Validating a Employee XML Based validation --------- end
		System.out.println("\nValidation Group example");

		validationErrors = validator.validate(emp, EmpIdCheck.class);
		///////////////////////////////////////////////
        //Below is where Employee.java

        //	@Min(value=1, groups=EmpIdCheck.class)
        //private int id;
        //////////////////////////////////////////////////////////////////
		if(!validationErrors.isEmpty()){
			for(ConstraintViolation<Employee> error : validationErrors){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
		
		System.out.println("\nValidation with inheritance example");

		//Validation inheritance and Property-level constraints example
		MyChildBean childBean = new MyChildBean();
		Set<ConstraintViolation<MyChildBean>> validationInheritanceErrors = validator.validate(childBean);
		
		if(!validationInheritanceErrors.isEmpty()){
			for(ConstraintViolation<MyChildBean> error : validationInheritanceErrors){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
		
		System.out.println("\nValidation in composition using @Valid annotation");

		//@Valid annotation - validation in composition example
		AnotherBean compositionBean = new AnotherBean();
		compositionBean.setChildBean(new MyChildBean());
		Set<ConstraintViolation<AnotherBean>> validationCompositionErrors = validator.validate(compositionBean);
		
		if(!validationCompositionErrors.isEmpty()){
			for(ConstraintViolation<AnotherBean> error : validationCompositionErrors){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
		
		System.out.println("\nParameter validation example");
		ParamValidationBean paramValidationBean = new ParamValidationBean("Pankaj");

        //printData is a method of ParamValidationBean
		Method method = ParamValidationBean.class.getMethod("printData", String.class);
		Object[] params = {"1234"};
		//ExecutableValidator is used to validate the parameters of a method.
		ExecutableValidator executableValidator = validator.forExecutables();
        //5. ExecutableValidator provide methods to validate constructor parameters and
        // return values too, these methods are validateReturnValue(),
        // validateConstructorParameters() and validateConstructorReturnValue()
		Set<ConstraintViolation<ParamValidationBean>> violations = 
				executableValidator.validateParameters(paramValidationBean, method, params);


		if(!violations.isEmpty()){
			for(ConstraintViolation<ParamValidationBean> error : violations){
				System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
				
			}
		}
		
	}

}
/*

Simple field level validation example
{org.hibernate.validator.constraints.Email.message}::email::not a well-formed email address
{javax.validation.constraints.Size.message}::name::size must be between 5 and 30
{org.hibernate.validator.constraints.CreditCardNumber.message}::creditCardNumber::invalid credit card number

XML Based validation example
{org.hibernate.validator.constraints.Email.message}::email::email::not a well-formed email address
{javax.validation.constraints.Size.message}::Name::name::size must be between 5 and 30
{org.hibernate.validator.constraints.CreditCardNumber.message}::123::creditCardNumber::invalid credit card number

Validation Group example
{javax.validation.constraints.Min.message}::id::must be greater than or equal to 1

Validation with inheritance example
{javax.validation.constraints.NotNull.message}::name::may not be null
{javax.validation.constraints.NotNull.message}::data::may not be null

Validation in composition using @Valid annotation
{javax.validation.constraints.NotNull.message}::childBean.name::may not be null
{javax.validation.constraints.NotNull.message}::childBean.data::may not be null

Parameter validation example
{javax.validation.constraints.Size.message}::printData.arg0::size must be between 5 and 2147483647
 */