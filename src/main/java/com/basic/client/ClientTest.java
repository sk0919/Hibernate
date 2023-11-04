package com.basic.client;

import com.basic.entities.Employee;
import com.basic.util.HibernateUtil;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

/**
* Author : Sumit Mishra
*/

public class ClientTest {
	
	public static void main(String[] args) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = createEmployee();
			session.beginTransaction();

			//Serializable  serializable = (Serializable) session.save(employee);
			Object  object =  session.save(employee);
			Integer id = (Integer)object;
			session.getTransaction().commit();

			System.out.println("Employee is created with ID =" +id);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/*
	public static void main(String[] args) {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Check MySQL database version
			String sql = "select version()";
			
			
//			//NativeQuery nativeQuery = session.createNativeQuery(sql);
//			NativeQuery<String> nativeQuery = session.createNativeQuery(sql, String.class);
//			Object object = nativeQuery.getSingleResult();
//			String result = (String)object;
//			System.out.println("MySql Database Version is:::");
//			System.out.println(result);
			
			
			//The method createNativeQuery(String) from the type QueryProducer is deprecated
			//String result = (String) session.createNativeQuery(sql).getSingleResult();   
			
			String result = (String) session.createNativeQuery(sql, String.class).getSingleResult();
			
			System.out.print("MySql Database Version is:::");
			System.out.println(result);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	  }
	
	*/
	
	
	
	
	private static Employee createEmployee(){
		Employee employee= new Employee();
		employee.setEmployeeName("john doe");
		employee.setEmail("john.doe@test.com");
		employee.setSalary(80000.00);
		employee.setDoj(new Date());
		return employee;
	}

}
