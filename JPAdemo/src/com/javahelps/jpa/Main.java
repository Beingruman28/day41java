package com.javahelps.jpa;

//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY= 	Persistence
			                .createEntityManagerFactory("JavaHelps");
	
	public static void main(String[]args) {
		
		create(1, "Alice",22);
		create(1, "bob",24);
		create(1, "charlie",25);
		
		update(2, "bob",25);
		
		delete(1);
		
		ENTITY_MANAGER_FACTORY.close();
	}
	
	public static void create(int id,String name , int age) {
		
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			
			Student stu = new Student();
			stu.setId(id);
			stu.setName(name);
			stu.setAge(age);
			
			manager.persist(stu);
			
			transaction.commit();
		}catch (Exception ex) { 
			if (transaction != null) { 
				transaction.rollback();
			}
			
			ex.printStackTrace();
		}finally {
			manager.close();
			}
		}
	/*
public static List readALL() {
		
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			
			//students = manager.createQuery("Select s from Student s" , Student.class).getResultList();
			
			
			transaction.commit();
		}catch (Exception ex) { 
			if (transaction != null) { 
				transaction.rollback();
			}
			
			ex.printStackTrace();
		}finally {
			manager.close();
			}
		//return Student;
		}
*/
public static void delete(int id) {
	
	EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
	EntityTransaction transaction = null;
	
	try {
		transaction = manager.getTransaction();
		transaction.begin();
		
		Student stu = manager.find(Student.class, id);
		
		manager.remove(stu);
		
		transaction.commit();
	}catch (Exception ex) { 
		if (transaction != null) { 
			transaction.rollback();
		}
		
		ex.printStackTrace();
	}finally {
		manager.close();
		}
	}
public static void update(int id,String name , int age) {
	
	EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
	EntityTransaction transaction = null;
	
	try {
		transaction = manager.getTransaction();
		transaction.begin();
		
		Student stu = manager.find(Student.class, id);
		
		stu.setName(name);
		stu.setAge(age);

		manager.persist(stu);
		
		transaction.commit();
	}catch (Exception ex) { 
		if (transaction != null) { 
			transaction.rollback();
		}
		
		ex.printStackTrace();
	}finally {
		manager.close();
		}
    }	
}

