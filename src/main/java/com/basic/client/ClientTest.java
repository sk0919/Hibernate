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
			
			int id = 4;
			double newSalary = 75643;
			//fetchById(session, id);
			//updateSalaryById(session, id, newSalary); //pros : update query will not be executed if no change in the data ,
			
			//createEmployee1(session);
			deleteById(session, id);
			
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	private static void deleteById(Session session, int employeeId) {
		Employee employee = session.get(Employee.class, employeeId) ;
		
		session.beginTransaction();
		
		//session.delete(employee); // The method delete(Object) from the type Session is deprecated
		session.remove(employee); // Use remove(Object) instead of delete(Object)
		
		session.getTransaction().commit();
		
	}
	*/
	
	private static void deleteById(Session session, int employeeId) {
		Employee employee = session.get(Employee.class, employeeId) ;
		
		if(employee != null) {
			session.beginTransaction();
			
			//session.delete(employee); // The method delete(Object) from the type Session is deprecated
			session.remove(employee); // Use remove(Object) instead of delete(Object)
			
			session.getTransaction().commit();
		
		}else {
			System.err.println("Employee doesn't exist with id: "+employeeId);
		}
		
		
	}


	private static void fetchById(Session session, int employeeId) {
		
		Employee employee = session.get(Employee.class, employeeId) ;
		System.out.println(employee);
		
	}
	
	private static void updateSalaryById(Session session, int employeeId, double newSalary) {
		Employee employee = session.get(Employee.class, employeeId) ;
		
		employee.setSalary(newSalary);
		
		session.beginTransaction();
		session.saveOrUpdate(employee); //The method saveOrUpdate(Object) from the type Session is deprecated
		session.getTransaction().commit();
	}
	
//	public static void main(String[] args) {
//		
//		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//			Employee employee = createEmployee1();
//			session.beginTransaction();
//
//			Object  object =  session.save(employee);
//			Integer id = (Integer)object;
//			session.getTransaction().commit();
//
//			System.out.println("Employee is created with ID =" +id);
//		}catch (HibernateException e) {
//			e.printStackTrace();
//		}
//	}

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
	
	private static void createEmployee1(Session session){
		Employee employee= new Employee();
		employee.setEmployeeName("jane doe");
		employee.setEmail("jane.doe@test.com");
		employee.setSalary(90000.00);
		employee.setDoj(new Date());
		
		
		
		session.beginTransaction();

		Object  object =  session.save(employee);
		Integer id = (Integer)object;
		session.getTransaction().commit();

		System.out.println("Employee is created with ID =" +id);
		
	}

}
